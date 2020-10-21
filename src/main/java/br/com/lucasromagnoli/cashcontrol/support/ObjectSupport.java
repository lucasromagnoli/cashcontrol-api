package br.com.lucasromagnoli.cashcontrol.support;

import java.util.Objects;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
public class ObjectSupport {
    private ObjectSupport() {}

    public static <T> T nvl(T a, T b) {
        return Objects.isNull(a) ? b : a;
    }
}
