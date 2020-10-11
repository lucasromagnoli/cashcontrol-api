package br.com.lucasromagnoli.cashcontrol.validator;

import lombok.Data;

@Data
public class ValidationMessage {
    private String field;
    private String message;
    private ValidatorType type;

    public ValidationMessage(String field, String message, ValidatorType type) {
        this.field = field;
        this.message = message;
        this.type = type;
    }
}
