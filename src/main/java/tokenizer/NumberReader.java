package tokenizer;

import exception.JSONException;
import model.Token;

import java.io.IOException;
import java.io.Reader;

import static utility.Helper.isDigit;
import static utility.Helper.isDigitOneToNine;

public class NumberReader implements JSONReader {

    public NumberReader() {
    }

    public Token read(Reader reader, int c) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (c == '-') {
            sb.append((char) c);
            c = reader.read();
            if (c == '0') {
                sb.append((char) c);
            } else if (isDigitOneToNine(c)) {
                do {
                    sb.append((char) c);
                    c = reader.read();
                } while (isDigit(c));
            } else {
                throw new JSONException("'-' is not followed by a digit");
            }
        } else if (c == '0') {
            sb.append((char) c);
        } else if (isDigitOneToNine(c)) {
            do {
                sb.append((char) c);
                c = reader.read();
            } while (isDigit(c));
        }
        return new Token(TokenType.NUMBER, sb.toString());
    }
}
