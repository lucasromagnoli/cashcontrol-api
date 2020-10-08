package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.javaee.useful.support.validation.EnumParseException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FrequencyTypeEnum {
    NONE(new String[] { "Nenhuma", "N", "NONE" }),
    DEBIT(new String[] { "Debito", "D", "Debit", "Débito" }),
    MONTHLY(new String[] { "Mensal", "M", "Monthly" }),
    YEARLY(new String[] { "Anual", "A", "Yearly" });

    private final String[] labels;

    FrequencyTypeEnum(String[] labels) {
        this.labels = labels;
    }

    public static FrequencyTypeEnum parse(String typeString) {
        for (FrequencyTypeEnum type : values()) {
            if (Arrays.stream(type.labels).anyMatch(t -> t.equalsIgnoreCase(typeString))) {
                return type;
            }
        }

        throw new EnumParseException();
    }
}
