package br.com.lucasromagnoli.cashcontrol.validator;

import java.util.function.Predicate;

public class PredicatesValidator {
    public static Predicate<String> stringLengthBetween(int min, int max) {
        return s -> s.length() >= min && s.length() <= max;
    }

    public static Predicate<Integer> integerValueBetween(int min, int max) {
        return i -> i >= min && i <= max;
    }
}
