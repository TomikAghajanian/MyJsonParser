package utility;

import tokenizer.TokenType;

import java.util.Map;

import static tokenizer.TokenType.*;

public final class Constants {
    public static final Map<Character, TokenType> TOKENS = Map.of(',', COMMA,
            ':', COLON,
            '{', START_OBJECT,
            '}', END_OBJECT,
            '[', START_ARRAY,
            ']', END_ARRAY);
}
