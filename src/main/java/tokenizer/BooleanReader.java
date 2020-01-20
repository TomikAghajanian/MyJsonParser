package tokenizer;

import model.Token;

import java.io.IOException;
import java.io.Reader;

public class BooleanReader implements JSONReader {

    public BooleanReader() {
    }

    public Token read(Reader reader, int c) throws IOException {
        if (c == 'f') {
            c = reader.read();
            if (c == 'a') {
                c = reader.read();
                if (c == 'l') {
                    c = reader.read();
                    if (c == 's') {
                        c = reader.read();
                        if (c == 'e') {
                            return new Token(TokenType.BOOLEAN, "false");
                        }
                    }
                }
            }
        } else if (c == 't') {
            c = reader.read();
            if (c == 'r') {
                c = reader.read();
                if (c == 'u') {
                    c = reader.read();
                    if (c == 'e') {
                        return new Token(TokenType.BOOLEAN, "true");
                    }
                }
            }
        }
        return null;
    }
}
