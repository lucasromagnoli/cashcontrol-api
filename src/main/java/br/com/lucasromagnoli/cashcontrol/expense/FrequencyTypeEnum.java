package br.com.lucasromagnoli.cashcontrol.expense;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Getter
public enum FrequencyTypeEnum {
    MONTHLY(new String[] { "Mensal", "M", "Monthly" }, 1),
    YEARLY(new String[] { "Anual", "A", "Yearly" }, 12);

    private final String[] labels;
    private final int months;

    FrequencyTypeEnum(String[] labels, int months) {
        this.labels = labels;
        this.months = months;
    }

    public static FrequencyTypeEnum parse(String typeString) {
        for (FrequencyTypeEnum type : values()) {
            if (Arrays.stream(type.labels).anyMatch(t -> t.equalsIgnoreCase(typeString))) {
                return type;
            }
        }

        return null;
    }
}
