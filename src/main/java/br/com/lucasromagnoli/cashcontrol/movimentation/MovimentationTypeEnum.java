package br.com.lucasromagnoli.cashcontrol.movimentation;

import lombok.Getter;

@Getter
public enum MovimentationTypeEnum {
    INCOME("Receita"), EXPENSE("Despesa");

    private final String label;

    MovimentationTypeEnum(String label) {
        this.label = label;
    }
}
