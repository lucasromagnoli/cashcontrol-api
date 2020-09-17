package br.com.lucasromagnoli.cashcontrol.expense;

import lombok.Getter;

@Getter
public enum FrequencyTypeEnum {
    NONE("Nenhuma"),
    MONTHLY("Mensal"),
    YEARLY("Anual");

    private final String label;

    FrequencyTypeEnum(String label) {
        this.label = label;
    }
}
