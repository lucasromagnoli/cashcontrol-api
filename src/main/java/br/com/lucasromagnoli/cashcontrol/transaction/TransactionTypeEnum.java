package br.com.lucasromagnoli.cashcontrol.transaction;

import br.com.lucasromagnoli.cashcontrol.exception.EnumParseException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TransactionTypeEnum {
    INCOME(new String[]{"Receita", "Income", "R", "I"}),
    EXPENSE(new String[]{"Despesa", "Expense", "D", "E"});

    private final String[] labels;

    TransactionTypeEnum(String[] labels) {
        this.labels = labels;
    }

    public static TransactionTypeEnum parse(String typeString) {
        for (TransactionTypeEnum type : values()) {
            if (Arrays.stream(type.labels).anyMatch(t -> t.equalsIgnoreCase(typeString))) {
                return type;
            }
        }
        
        throw new EnumParseException();
    }
}

