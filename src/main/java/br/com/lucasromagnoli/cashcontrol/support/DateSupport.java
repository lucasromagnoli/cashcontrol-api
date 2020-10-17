package br.com.lucasromagnoli.cashcontrol.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateSupport {
    private DateSupport() {}

    public static LocalDate fromString(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        try {
            return LocalDate.parse(date, formatter);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
