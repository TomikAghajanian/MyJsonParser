package analyzer;

import exception.JSONException;
import model.JSON;
import model.Token;
import tokenizer.TokenType;

import java.util.List;

public interface SyntacticalAnalyzer {

    public JSON analyze(List<Token> tokens) throws JSONException;

    default boolean areMatchingTypes(Token token, TokenType tokenType) {
        return token.getType() == tokenType;
    }

    default Token consumeFirst(List<Token> tokens) {
        return tokens.remove(0);
    }

    default Token peekFirst(List<Token> tokens) {
        return tokens.get(0);
    }

    default boolean isPrimitive(Token token) {
        TokenType type = token.getType();
        return type == TokenType.BOOLEAN || type == TokenType.NULL || type == TokenType.NUMBER || type == TokenType.STRING;
    }

}
