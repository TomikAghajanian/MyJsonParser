package analyzer;

import exception.JSONException;
import model.JSON;
import model.Token;
import org.junit.Before;
import org.junit.Test;
import tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JSONAnalyzerTest {

    private SyntacticalAnalyzer jsonAnalyzer;

    @Before
    public void setup(){
        jsonAnalyzer = new JSONAnalyzer();
    }

    @Test
    public void shouldCreateJSONObjectUsingTokensList() throws JSONException {
        List<Token> tokens = new ArrayList<>();

        tokens.add(new Token(TokenType.START_OBJECT, "{"));
        tokens.add(new Token(TokenType.STRING, "hello"));
        tokens.add( new Token(TokenType.COLON, ":"));
        tokens.add(new Token(TokenType.STRING, "world"));
        tokens.add(new Token(TokenType.END_OBJECT, "}"));

        JSON json = jsonAnalyzer.analyze(tokens);
        assertEquals(json.getAsJSONObject().getPrimitive("hello").getAsString(), "world");
    }
}
