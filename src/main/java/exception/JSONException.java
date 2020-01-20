package exception;

import java.io.IOException;

public class JSONException extends IOException {
    public JSONException(String msg) {
        super(msg);
    }
}
