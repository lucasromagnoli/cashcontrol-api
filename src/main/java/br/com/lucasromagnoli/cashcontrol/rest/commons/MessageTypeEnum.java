package br.com.lucasromagnoli.cashcontrol.rest.commons;

public enum MessageTypeEnum {
    SUCCESS("Sucesso"),
    WARNING("Aviso"),
    INFORMATION("Informação"),
    ERROR("Erro");

    private final String label;

    MessageTypeEnum(String label) {
        this.label = label;
    }
}
