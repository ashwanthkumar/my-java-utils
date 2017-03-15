package in.ashwanthkumar.utils.lang;

public class StringUtils {
    public static boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }

    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    public static boolean isBlank(String text) {
        return isEmpty(trim(text));
    }

    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }

    public static boolean startsWith(String str, String prefix) {
        return isNotEmpty(str) && str.startsWith(prefix);
    }

    public static boolean endsWith(String str, String suffix) {
        return isNotEmpty(str) && str.endsWith(suffix);
    }

    public static int size(String str) {
        if (isNotEmpty(str)) return str.length();
        else return 0;
    }

    public static String trim(String input) {
        return (isEmpty(input)) ? "" : input.trim();
    }
}
