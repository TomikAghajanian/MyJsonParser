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
        String test1 = "{\"menu\": {\n" +
                "    \"header\": \"SVG Viewer\",\n" +
                "    \"items\": [\n" +
                "        {\"id\": \"Open\"},\n" +
                "        {\"id\": \"OpenNew\", \"label\": \"Open New\"},\n" +
                "        {\"id\": \"ZoomIn\", \"label\": \"Zoom In\"},\n" +
                "        {\"id\": \"ZoomOut\", \"label\": \"Zoom Out\"},\n" +
                "        {\"id\": \"OriginalView\", \"label\": \"Original View\"},\n" +
                "        {\"id\": \"Quality\"},\n" +
                "        {\"id\": \"Pause\"},\n" +
                "        {\"id\": \"Mute\"},\n" +
                "        {\"id\": \"Find\", \"label\": \"Find...\"},\n" +
                "        {\"id\": \"FindAgain\", \"label\": \"Find Again\"},\n" +
                "        {\"id\": \"Copy\"},\n" +
                "        {\"id\": \"CopyAgain\", \"label\": \"Copy Again\"},\n" +
                "        {\"id\": \"CopySVG\", \"label\": \"Copy SVG\"},\n" +
                "        {\"id\": \"ViewSVG\", \"label\": \"View SVG\"},\n" +
                "        {\"id\": \"ViewSource\", \"label\": \"View Source\"},\n" +
                "        {\"id\": \"SaveAs\", \"label\": \"Save As\"},\n" +
                "        {\"id\": \"Help\"},\n" +
                "        {\"id\": \"About\", \"label\": \"About Adobe CVG Viewer...\"}\n" +
                "    ]\n" +
                "}}";
        Parser parser = injector.getInstance(JSONParser.class);
        System.out.println(test1);
        JSON json2 = parser.parse(test1);
        System.out.println(json2.getAsJSONObject().getJSONObject("menu"));
    }
}
