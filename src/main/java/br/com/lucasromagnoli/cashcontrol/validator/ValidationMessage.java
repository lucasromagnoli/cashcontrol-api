package br.com.lucasromagnoli.cashcontrol.validator;

import lombok.Data;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
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
