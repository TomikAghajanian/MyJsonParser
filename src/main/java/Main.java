import com.google.inject.Guice;
import com.google.inject.Injector;
import configuration.AnalyzerConfiguration;
import configuration.TokenizerConfiguration;
import configuration.ParserConfiguration;
import model.JSON;
import parser.JSONParser;
import parser.Parser;

public class Main {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new AnalyzerConfiguration(), new TokenizerConfiguration(), new ParserConfiguration());
        String jsonString1 = "   {" +
                "\"menu\": {" +
                "\"header\": \"SVG Viewer\"," +
                "\"items\": [{" +
                "\"id\": \"Open\"" +
                "}]" +
                "}" +
                "}";
        String jsonString2 = "[{" +
                "\"id\": \"Open\"" +
                "}]";
        Parser parser = injector.getInstance(JSONParser.class);
        JSON json = parser.parse(jsonString1);
        System.out.println(json.getAsJSONObject().getJSONObject("menu"));
        JSON json2 = parser.parse(jsonString2);
        System.out.println(json2.getAsJSONArray().get(0).getAsJSONObject().get("id"));
    }
}
