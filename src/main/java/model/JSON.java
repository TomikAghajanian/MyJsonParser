package model;

public abstract class JSON {
    public boolean isJSONArray() {
        return this instanceof JSONArray;
    }

    public boolean isJSONObject() {
        return this instanceof JSONObject;
    }

    public boolean isPrimitive() {
        return this instanceof Primitive;
    }

    public JSONObject getAsJSONObject() {
        if (isJSONObject()) {
            return (JSONObject) this;
        }
        throw new IllegalStateException("Not a JSON Object");
    }

    public JSONArray getAsJSONArray() {
        if (isJSONArray()) {
            return (JSONArray) this;
        }
        throw new IllegalStateException("Not a JSON Array");
    }

    public Primitive getAsJSONPrimitive() {
        if (isPrimitive()) {
            return (Primitive) this;
        }
        throw new IllegalStateException("Not a JSON Primitive");
    }

    public boolean getAsBoolean() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String getAsString() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int getAsInt() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

}
