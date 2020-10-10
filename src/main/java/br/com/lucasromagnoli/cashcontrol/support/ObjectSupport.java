package br.com.lucasromagnoli.cashcontrol.support;

import java.util.Objects;

public class ObjectSupport {
    private ObjectSupport() {}

    public static <T> T nvl(T a, T b) {
        return Objects.isNull(a) ? b : a;
    }
}
