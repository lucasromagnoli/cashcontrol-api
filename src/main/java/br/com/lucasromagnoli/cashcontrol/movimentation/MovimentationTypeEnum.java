package br.com.lucasromagnoli.cashcontrol.movimentation;

import br.com.lucasromagnoli.javaee.useful.support.validation.EnumParseException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MovimentationTypeEnum {
    INCOME(new String[]{"Receita", "Income", "R", "I"}),
    EXPENSE(new String[]{"Despesa", "Expense", "D", "E"});

    private final String[] labels;

    MovimentationTypeEnum(String[] labels) {
        this.labels = labels;
    }

    public static MovimentationTypeEnum parse(String typeString) {
        for (MovimentationTypeEnum type : values()) {
            if (Arrays.stream(type.labels).anyMatch(t -> t.equalsIgnoreCase(typeString))) {
                return type;
            }
        }
        
        throw new EnumParseException();
    }
}

