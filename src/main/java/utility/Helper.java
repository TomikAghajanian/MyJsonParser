package utility;

public class Helper {

    public static boolean isSpace(int c) {
        return c >= 0 && c <= ' ';
    }

    public static boolean isNum(int c){
        return isDigit(c) || c == '-';
    }

    public static boolean isDigit(int c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isDigitOne2Nine(int c) {
        return c >= '1' && c <= '9';
    }
}
