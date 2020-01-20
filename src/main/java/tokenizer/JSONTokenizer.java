package tokenizer;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import exception.JSONException;
import model.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static utility.Constants.TOKENS;
import static utility.Helper.isNum;
import static utility.Helper.isSpace;

public class JSONTokenizer implements Tokenizer<Token> {

    private JSONReader booleanReader;
    private JSONReader stringReader;
    private JSONReader numberReader;
    private JSONReader nullReader;

    @Inject
    public JSONTokenizer(@Named("BooleanReader") JSONReader booleanReader,
                         @Named("StringReader") JSONReader stringReader,
                         @Named("NumberReader") JSONReader numberReader,
                         @Named("NullReader") JSONReader nullReader) {
        this.booleanReader = booleanReader;
        this.stringReader = stringReader;
        this.numberReader = numberReader;
        this.nullReader = nullReader;
    }

    public List<Token> process(String jsonInput) throws Exception {
        List<Token> tokens = new ArrayList<>();
        Reader reader = new BufferedReader(new StringReader(jsonInput));
        Token token = evaluate(reader);
        while (token.getType() != TokenType.END_DATA) {
            tokens.add(token);
            token = evaluate(reader);
        }
        return tokens;
    }

    private Token evaluate(Reader reader) throws Exception {
        int c = reader.read();
        while (isSpace(c)) {
            c = reader.read();
        }
        Token booleanChecks = booleanChecks(reader, c);
        if (booleanChecks != null) {
            return booleanChecks;
        }
        return characterChecks(c);
    }

    private Token characterChecks(int c) throws IOException {
        if (TOKENS.get((char) c) != null) {
            return new Token(TOKENS.get((char) c), Character.toString((char) c));
        } else {
            throw new JSONException("Invalid JSONObject input.");
        }
    }

    private Token booleanChecks(Reader reader, int c) throws IOException {
        if (c == -1) {
            return new Token(TokenType.END_DATA, "EOF");
        }
        if (isNum(c)) {
            return numberReader.read(reader, c);
        }
        if (c == '"') {
            return stringReader.read(reader, c);
        }
        if (c == 'n') {
            Token nullReaderResult = nullReader.read(reader, c);
            if (nullReaderResult != null) {
                return nullReaderResult;
            } else {
                throw new JSONException("Invalid JSONObject input.");
            }
        }
        if (c == 'f' || c == 't') {
            Token booleanReaderResult = booleanReader.read(reader, c);
            if (booleanReaderResult != null) {
                return booleanReaderResult;
            } else {
                throw new JSONException("Invalid JSONObject input.");
            }

        }
        return null;
    }
}
