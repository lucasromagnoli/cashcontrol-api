package br.com.lucasromagnoli.cashcontrol.category;

import lombok.Getter;

@Getter
public enum CategoryType {
    R("Receita"),D("Despesa");

    private final String label;

    CategoryType(String label) {
        this.label = label;
    }


}
