package parser;

import model.JSON;

public interface Parser {

    public JSON parse(String s) throws Exception;
}
