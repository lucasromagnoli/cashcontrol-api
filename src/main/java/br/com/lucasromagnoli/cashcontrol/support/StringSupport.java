package br.com.lucasromagnoli.cashcontrol.support;

public class StringSupport {
    private StringSupport() {
    }

    public static String camelToSnake(String target) {
        return target
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
    }
}
