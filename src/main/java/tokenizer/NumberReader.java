package tokenizer;

import exception.JSONException;
import model.Token;

import java.io.IOException;
import java.io.Reader;

import static utility.Helper.isDigit;
import static utility.Helper.isDigitOne2Nine;

public class NumberReader implements JSONReader {

    public NumberReader() {
    }

    public Token read(Reader reader, int c) throws IOException {
        return readNum(reader);
    }

    private Token readNum(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c = reader.read();
        if (c == '-') {
            sb.append((char) c);
            c = reader.read();
            if (c == '0') {
                sb.append((char) c);
            } else if (isDigitOne2Nine(c)) {
                do {
                    sb.append((char) c);
                    c = reader.read();
                } while (isDigit(c));
            } else {
                throw new JSONException("- not followed by digit");
            }
        } else if (c == '0') {
            sb.append((char) c);
        } else if (isDigitOne2Nine(c)) {
            do {
                sb.append((char) c);
                c = reader.read();
            } while (isDigit(c));
        }
        return new Token(TokenType.NUMBER, sb.toString());
    }
}
