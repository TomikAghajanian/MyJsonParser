package tokenizer;

import java.util.List;

public interface Tokenizer<T> {

    public List<T> process(String input) throws Exception;
}
