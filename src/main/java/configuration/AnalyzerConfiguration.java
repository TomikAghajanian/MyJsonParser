package configuration;

import analyzer.JSONAnalyzer;
import analyzer.JSONArrayAnalyzer;
import analyzer.JSONObjectAnalyzer;
import analyzer.SyntacticalAnalyzer;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class AnalyzerConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(SyntacticalAnalyzer.class).annotatedWith(Names.named("JsonAnalyzer")).to(JSONAnalyzer.class);
        bind(SyntacticalAnalyzer.class).annotatedWith(Names.named("JsonObjectAnalyzer")).to(JSONObjectAnalyzer.class);
        bind(SyntacticalAnalyzer.class).annotatedWith(Names.named("JsonArrayAnalyzer")).to(JSONArrayAnalyzer.class);
    }
}
