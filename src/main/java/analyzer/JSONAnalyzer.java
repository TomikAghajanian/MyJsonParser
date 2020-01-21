package analyzer;

import exception.JSONException;
import model.*;
import tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;

import static tokenizer.TokenType.COLON;
import static tokenizer.TokenType.COMMA;
import static tokenizer.TokenType.END_OBJECT;
import static tokenizer.TokenType.START_ARRAY;
import static tokenizer.TokenType.START_OBJECT;
import static tokenizer.TokenType.STRING;

public class JSONAnalyzer implements SyntacticalAnalyzer {

    public JSONAnalyzer() {
    }

    public JSON analyze(List<Token> tokens) throws JSONException {
        if (tokens.isEmpty()) {
            return null;
        }
        Token token = peekFirst(tokens);
        if (areMatchingTypes(token, START_OBJECT)) {
            return processJSONObject(tokens);
        } else if (areMatchingTypes(token, START_ARRAY)) {
            return processJSONArray(tokens);
        } else if (isPrimitive(token)) {
            consumeFirst(tokens);
            return new Primitive(token.getValue());
        } else {
            throw new JSONException("Invalid input");
        }
    }

    private JSON processJSONObject(List<Token> tokens) throws JSONException {
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
                    jsonObject.put(key, processJSONArray(tokens));
                    break;
                case START_OBJECT:
                    jsonObject.put(key, analyze(tokens));
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

    private JSON processJSONArray(List<Token> tokens) throws JSONException {
        if (tokens.isEmpty()) {
            return null;
        }
        List<JSON> jsonElements = new ArrayList<>();
        Token mustBeOpenBrackets = consumeFirst(tokens);
        if (!areMatchingTypes(mustBeOpenBrackets, START_ARRAY)) {
            throw new JSONException("Invalid JSONObject input.");
        }
        JSONArray array;
        Token token = peekFirst(tokens);
        if (areMatchingTypes(token, TokenType.START_ARRAY)) {
            array = processJSONArray(tokens).getAsJSONArray();
            jsonElements.add(array);
            Token tmpToken = peekFirst(tokens);
            if (areMatchingTypes(tmpToken, TokenType.COMMA)) {
                consumeFirst(tokens);
                appendToJSONElements(tokens, jsonElements);
            }
        } else if (isPrimitive(token)) {
            appendToJSONElements(tokens, jsonElements);
        } else if (areMatchingTypes(token, TokenType.START_OBJECT)) {
            jsonElements.add(processJSONObject(tokens));
            while (areMatchingTypes(peekFirst(tokens), TokenType.COMMA)) {
                consumeFirst(tokens);
                jsonElements.add(analyze(tokens));
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

    private void appendToJSONElements(List<Token> tokens, List<JSON> jsonList) throws JSONException {
        jsonList.add(new Primitive(consumeFirst(tokens).getValue()));
        Token token = peekFirst(tokens);
        if (areMatchingTypes(token, TokenType.COMMA)) {
            consumeFirst(tokens);
            Token tmpToken = peekFirst(tokens);
            if (isPrimitive(tmpToken)) {
                appendToJSONElements(tokens, jsonList);
            } else if (areMatchingTypes(tmpToken, TokenType.START_OBJECT)) {
                jsonList.add(processJSONObject(tokens));
            } else if (areMatchingTypes(tmpToken, TokenType.START_ARRAY)) {
                jsonList.add(processJSONArray(tokens));
            } else {
                throw new JSONException("Invalid JSONObject input.");
            }
        } else if (!areMatchingTypes(token, TokenType.END_ARRAY)) {
            throw new JSONException("Invalid JSONObject input.");
        }
    }

}
