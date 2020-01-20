package tokenizer;

import exception.JSONException;
import model.Token;

import java.io.IOException;
import java.io.Reader;

public class StringReader implements JSONReader {

    public StringReader() {
    }

    public Token read(Reader reader, int c) throws IOException {
        return readString(reader);
    }

    private Token readString(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int c = reader.read();
            if (isEscape(reader, c)) {
                sb.append("\\").append((char) c);
            } else if (c == '"') {
                return new Token(TokenType.STRING, sb.toString());
            } else if (c == '\r' || c == '\n') {
                throw new JSONException("Invalid JSON input.");
            } else {
                sb.append((char) c);
            }
        }
    }

    private boolean isEscape(Reader reader, int c) throws IOException {
        if (c == '\\') {
            c = reader.read();
            if (c == '"' ||
                    c == '\\' ||
                    c == '/' ||
                    c == 'b' ||
                    c == 'f' ||
                    c == 'n' ||
                    c == 't' ||
                    c == 'r' ||
                    c == 'u') {
                return true;
            } else {
                throw new JSONException("Invalid JSON input");
            }
        } else {
            return false;
        }
    }
}
