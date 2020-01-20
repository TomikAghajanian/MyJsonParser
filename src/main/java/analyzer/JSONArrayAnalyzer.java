package analyzer;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import exception.JSONException;
import model.JSON;
import model.JSONArray;
import model.Primitive;
import model.Token;
import tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;

import static tokenizer.TokenType.START_ARRAY;

public class JSONArrayAnalyzer implements SyntacticalAnalyzer {
    private SyntacticalAnalyzer jsonObjectParser;

    @Inject
    public JSONArrayAnalyzer(@Named("JsonObjectAnalyzer") SyntacticalAnalyzer jsonObjectParser) {
        this.jsonObjectParser = jsonObjectParser;
    }

    public JSON analyze(List<Token> tokens) throws JSONException {
        if (tokens.isEmpty()) {
            return null;
        }
        List<JSON> jsonElements = new ArrayList<>();
        Token mustBeOpenBrackets = consumeFirst(tokens);
        if(!areMatchingTypes(mustBeOpenBrackets, START_ARRAY)){
            throw new JSONException("Invalid JSONObject input.");
        }
        JSONArray array;
        Token token = peekFirst(tokens);
        if (areMatchingTypes(token, TokenType.START_ARRAY)) {
            array = analyze(tokens).getAsJSONArray();
            jsonElements.add(array);
            if (areMatchingTypes(token, TokenType.COMMA)) {
                consumeFirst(tokens);
                appendToJsonElements(tokens, jsonElements);
            }
        } else if (isPrimitive(token)) {
            appendToJsonElements(tokens, jsonElements);
        } else if (areMatchingTypes(token, TokenType.START_OBJECT)) {
            jsonElements.add(jsonObjectParser.analyze(tokens));
            while (areMatchingTypes(token, TokenType.COMMA)) {
                consumeFirst(tokens);
                jsonElements.add(jsonObjectParser.analyze(tokens));
            }
        } else if (areMatchingTypes(token, TokenType.END_ARRAY)) {
            consumeFirst(tokens);
            array = new JSONArray();
            array.addAll(jsonElements);
            return array;
        }
        consumeFirst(tokens);
        array = new JSONArray();
        array.addAll(jsonElements);
        return array;
    }

    private void appendToJsonElements(List<Token> tokens, List<JSON> jsonList) throws JSONException {
        Token token = consumeFirst(tokens);
        jsonList.add(new Primitive(token.getValue()));
        if (areMatchingTypes(token, TokenType.COMMA)) {
            Token tmpToken = consumeFirst(tokens);
            if (isPrimitive(tmpToken)) {
                appendToJsonElements(tokens, jsonList);
            } else if (areMatchingTypes(tmpToken, TokenType.START_OBJECT)) {
                jsonList.add(jsonObjectParser.analyze(tokens));
            } else if (areMatchingTypes(tmpToken, TokenType.START_ARRAY)) {
                jsonList.add(analyze(tokens));
            } else {
                throw new JSONException("Invalid JSONObject input.");
            }
        } else {
            throw new JSONException("Invalid JSONObject input.");
        }
    }
}
