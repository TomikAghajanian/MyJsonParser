package tokenizer;

public final class TestConstants {
    public static final String JSON_ONE = "   {" +
            "\"menu\": {" +
            "\"header\": \"SVG Viewer\"," +
            "\"items\": [{" +
            "\"id\": \"Open\"" +
            "}]" +
            "}" +
            "}";

    public static final String JSON_TWO = "[{" +
            "\"id\": \"Open\"" +
            "}]";

    private TestConstants() {
        throw new AssertionError();
    }
}
