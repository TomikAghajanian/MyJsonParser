package model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONObject extends JSON {

    private Map<String, JSON> jsonMap;

    public JSONObject() {
        this.jsonMap = new HashMap<>();
    }

    public void put(String key, JSON value) {
        this.jsonMap.put(key, value);
    }

    public JSON get(String key) {
        return jsonMap.get(key);
    }

    public JSONObject getJSONObject(String key) {
        return (JSONObject) jsonMap.get(key);
    }

    public Primitive getPrimary(String key) {
        return (Primitive) jsonMap.get(key);
    }

    public JSONArray getJSONArray(String key) {
        return (JSONArray) jsonMap.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        AtomicInteger size = new AtomicInteger(jsonMap.size());
        jsonMap.keySet().forEach(key -> {
            sb.append(key).append(" : ").append(jsonMap.get(key).toString());
            if (size.decrementAndGet() != 0) {
                sb.append(", ");
            }
        });
        sb.append(" }");
        return sb.toString();
    }
}
