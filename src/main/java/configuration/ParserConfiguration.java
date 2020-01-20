package configuration;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import parser.JSONParser;
import parser.Parser;
import tokenizer.*;

public class ParserConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(Parser.class).annotatedWith(Names.named("JsonParser")).to(JSONParser.class);
        bind(JSONReader.class).annotatedWith(Names.named("NullReader")).to(NullReader.class);
        bind(JSONReader.class).annotatedWith(Names.named("NumberReader")).to(NumberReader.class);
        bind(JSONReader.class).annotatedWith(Names.named("StringReader")).to(StringReader.class);
        bind(JSONReader.class).annotatedWith(Names.named("BooleanReader")).to(BooleanReader.class);
    }
}
