package model;

public class Primitive extends JSON {
    private Object value;

    public Primitive(String value) {
        this.value = value;
    }

    @Override
    public int getAsInt() {
        return Integer.parseInt(getAsString());
    }

    @Override
    public String getAsString() {
        if (isBoolean()) {
            return ((Boolean) value).toString();
        } else {
            return (String) value;
        }
    }

    @Override
    public boolean getAsBoolean() {
        if (isBoolean()) {
            return (Boolean) value;
        }
        return Boolean.parseBoolean(getAsString());
    }

    public boolean isString() {
        return value instanceof String;
    }

    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    @Override
    public String toString() {
        return getAsString();
    }
}
