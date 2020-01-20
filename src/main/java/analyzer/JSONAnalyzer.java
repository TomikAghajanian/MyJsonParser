package analyzer;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import exception.JSONException;
import model.JSON;
import model.Token;

import java.util.List;

import static tokenizer.TokenType.START_ARRAY;
import static tokenizer.TokenType.START_OBJECT;

public class JSONAnalyzer implements SyntacticalAnalyzer {

    private SyntacticalAnalyzer jsonObjectAnalyzer;
    private SyntacticalAnalyzer jsonArrayAnalyzer;

    @Inject
    public JSONAnalyzer(@Named("JsonObjectAnalyzer") SyntacticalAnalyzer jsonObjectAnalyzer,
                        @Named("JsonArrayAnalyzer") SyntacticalAnalyzer jsonArrayAnalyzer) {
        this.jsonObjectAnalyzer = jsonObjectAnalyzer;
        this.jsonArrayAnalyzer = jsonArrayAnalyzer;
    }

    @Override
    public JSON analyze(List<Token> tokens) throws JSONException {
        if (tokens.isEmpty()) {
            return null;
        }
        Token mustBeOpeningBraces = peekFirst(tokens);
        if (areMatchingTypes(mustBeOpeningBraces, START_OBJECT)) {
            return jsonObjectAnalyzer.analyze(tokens);
        } else if (areMatchingTypes(mustBeOpeningBraces, START_ARRAY)) {
            return jsonArrayAnalyzer.analyze(tokens);
        } else {
            throw new JSONException("Invalid input");
        }
    }
}
