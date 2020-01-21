package configuration;

import analyzer.JSONAnalyzer;
import analyzer.SyntacticalAnalyzer;
import com.google.inject.AbstractModule;

public class AnalyzerConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(SyntacticalAnalyzer.class).to(JSONAnalyzer.class);
    }
}
