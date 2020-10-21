package br.com.lucasromagnoli.cashcontrol.validator;

import java.math.BigDecimal;
import java.util.function.Predicate;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
public class PredicatesValidator {
    public static Predicate<String> stringLengthBetween(int min, int max) {
        return s -> s.length() >= min && s.length() <= max;
    }

    public static Predicate<Integer> integerValueBetween(int min, int max) {
        return i -> i >= min && i <= max;
    }

    public static Predicate<Integer> integerMinValue(int min) {
        return i -> i >= min;
    }

    public static Predicate<BigDecimal> bigDecimalFloatMinValue(float min) {
        return b -> b.floatValue() >= min;
    }
}
