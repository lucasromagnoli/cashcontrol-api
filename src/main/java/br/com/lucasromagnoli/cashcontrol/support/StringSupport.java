package br.com.lucasromagnoli.cashcontrol.support;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
public class StringSupport {
    private StringSupport() {
    }

    public static String camelToSnake(String target) {
        return target
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .replace(".", "_")
                .toLowerCase();
    }

    public static String firstLetterToUpperCase(String target) {
        return new StringBuilder(target)
                .replace(0, 1, String.valueOf(target.charAt(0)).toUpperCase())
                .toString();
    }
}
