package parser;

import analyzer.SyntacticalAnalyzer;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import exception.JSONException;
import model.JSON;
import model.Token;
import tokenizer.Tokenizer;

import java.util.List;

public class JSONParser implements Parser {
    private SyntacticalAnalyzer jsonAnalyzer;
    private Tokenizer<Token> jsonTokenizer;

    @Inject
    public JSONParser(@Named("JsonAnalyzer") SyntacticalAnalyzer jsonAnalyzer,
                      @Named("JsonTokenizerProcessor") Tokenizer<Token> jsonTokenizer) {
        this.jsonAnalyzer = jsonAnalyzer;
        this.jsonTokenizer = jsonTokenizer;
    }

    @Override
    public JSON parse(String s) throws Exception {
        return getJSON(jsonTokenizer.process(s));
    }

    private JSON getJSON(List<Token> tokens) throws JSONException{
        return jsonAnalyzer.analyze(tokens);
    }
}
