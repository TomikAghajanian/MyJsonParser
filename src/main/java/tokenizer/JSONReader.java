package tokenizer;

import model.Token;

import java.io.IOException;
import java.io.Reader;

public interface JSONReader {

    public Token read(Reader reader, int c) throws IOException;
}
