package analyzer;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import exception.JSONException;
import model.JSON;
import model.JSONObject;
import model.Primitive;
import model.Token;

import java.util.List;

import static tokenizer.TokenType.*;

public class JSONObjectAnalyzer implements SyntacticalAnalyzer {
    private SyntacticalAnalyzer jsonArrayParser;

    @Inject
    public JSONObjectAnalyzer(@Named("JsonArrayAnalyzer") SyntacticalAnalyzer jsonArrayParser) {
        this.jsonArrayParser = jsonArrayParser;
    }

    public JSON analyze(List<Token> tokens) throws JSONException {
        if (tokens.isEmpty()) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        Token mustBeOpeningCurlyBraces = peekFirst(tokens);
        if (!areMatchingTypes(mustBeOpeningCurlyBraces, START_OBJECT)) {
            throw new JSONException("Invalid input");
        }
        consumeFirst(tokens);
        Token token = peekFirst(tokens);
        switch (token.getType()) {
            case END_OBJECT:
                consumeFirst(tokens);
                return jsonObject;
            case STRING:
                updateObjectMap(jsonObject, tokens);
                break;
            default:
                throw new JSONException("Invalid JSONObject input.");
        }
        return jsonObject;
    }

    private void updateObjectMap(JSONObject jsonObject, List<Token> tokens) throws JSONException {
        String key = consumeFirst(tokens).getValue();
        Token mustBeColon = consumeFirst(tokens);
        if (!areMatchingTypes(mustBeColon, COLON)) {
            throw new JSONException("Invalid JSONObject input.");
        }
        Token afterColonToken = peekFirst(tokens);
        if (isPrimitive(afterColonToken)) {
            JSON primary = new Primitive(consumeFirst(tokens).getValue());
            jsonObject.put(key, primary);
        } else {
            switch (afterColonToken.getType()) {
                case START_ARRAY:
                    jsonObject.put(key, jsonArrayParser.analyze(tokens));
                    break;
                case START_OBJECT:
                    jsonObject.put(key,  analyze(tokens));
                    break;
                default:
                    throw new JSONException("Invalid JSONObject input.");
            }
        }
        Token tmpToken = peekFirst(tokens);
        if (areMatchingTypes(tmpToken, COMMA)) {
            consumeFirst(tokens);
            if (areMatchingTypes(peekFirst(tokens), STRING)) {
                updateObjectMap(jsonObject, tokens);
            }
        } else if (areMatchingTypes(tmpToken, END_OBJECT)) {
            consumeFirst(tokens);
        } else {
            throw new JSONException("Invalid JSON input.");
        }
    }
}
