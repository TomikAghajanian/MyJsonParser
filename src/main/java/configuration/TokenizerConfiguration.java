package configuration;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import model.Token;
import tokenizer.Tokenizer;
import tokenizer.JSONTokenizer;

public class TokenizerConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<Tokenizer<Token>>(){}).annotatedWith(Names.named("JsonTokenizerProcessor")).to(JSONTokenizer.class);
    }
}
