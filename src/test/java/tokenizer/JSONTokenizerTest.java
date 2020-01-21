package tokenizer;

import model.Token;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static util.TestConstants.JSON_ONE;
import static util.TestConstants.JSON_TWO;

public class JSONTokenizerTest {
    private Tokenizer<Token> jsonTokenizer;

    @Before
    public void setup(){
        jsonTokenizer = new JSONTokenizer(new BooleanReader(), new StringReader(), new NumberReader(), new NullReader());
    }

    @Test
    public void shouldTokenizeJsonStringObject() throws Exception {
        List<Token> jsonTokens = jsonTokenizer.process(JSON_ONE);

        assertEquals(jsonTokens.get(0).getValue(), "{");
        assertEquals(jsonTokens.get(0).getType(), TokenType.START_OBJECT);
        assertEquals(jsonTokens.size(), 19);
    }

    @Test
    public void shouldTokenizeJsonStringArray() throws Exception {
        List<Token> jsonTokens = jsonTokenizer.process(JSON_TWO);

        assertEquals(jsonTokens.get(0).getValue(), "[");
        assertEquals(jsonTokens.get(0).getType(), TokenType.START_ARRAY);
        assertEquals(jsonTokens.size(), 7);
    }
}
