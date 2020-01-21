package parser;

import analyzer.JSONAnalyzer;
import analyzer.JSONArrayAnalyzer;
import analyzer.JSONObjectAnalyzer;
import analyzer.SyntacticalAnalyzer;
import model.Token;
import org.junit.Before;
import tokenizer.Tokenizer;

public class JSONParserTest {

    private SyntacticalAnalyzer jsonAnalyzer;
    private SyntacticalAnalyzer jsonArrayAnalyzer;
    private SyntacticalAnalyzer jsonObjectAnalyzer;
    private Tokenizer<Token> jsonTokenizer;
    private Parser jsonParser;

    @Before
    public void setup(){
        jsonArrayAnalyzer = new JSONArrayAnalyzer(new JSONObjectAnalyzer());
        jsonObjectAnalyzer = new JSONObjectAnalyzer();
        jsonAnalyzer = new JSONAnalyzer(n);
        jsonParser = new JSONParser(jsonAnalyzer, jsonTokenizer);
    }
}
