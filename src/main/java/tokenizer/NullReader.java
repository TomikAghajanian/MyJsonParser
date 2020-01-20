package tokenizer;

import model.Token;

import java.io.IOException;
import java.io.Reader;

import static tokenizer.TokenType.NULL;

public class NullReader implements JSONReader {

    public NullReader() {
    }

    public Token read(Reader reader, int c) throws IOException {
        if (c == 'n') {
            c = reader.read();
            if (c == 'u') {
                c = reader.read();
                if (c == 'l') {
                    c = reader.read();
                    if (c == 'l') {
                        return new Token(NULL, null);
                    }
                }
            }
        }
        return null;
    }
}
