package br.com.lucasromagnoli.cashcontrol.exception;

import br.com.lucasromagnoli.cashcontrol.validator.ValidatorType;
import lombok.Getter;

import java.lang.reflect.Field;

public class InputValidationException extends ValidationException {

    private final String field;
    private String message;

    public InputValidationException(String field) {
        this.field = field;
    }

    public InputValidationException(Field field) {
        this.field = field.getName();
    }

    public InputValidationException(String field, String message) {
        this.message = message;
        this.field = field;
    }

    public InputValidationException(Field field, String message) {
        this.message = message;
        this.field = field.getName();
    }

    @Override
    public ValidatorType getValidatorType() {
        return ValidatorType.INPUT;
    }

    @Override
    public String getHumanMessage() {
        String template = "[%s] - %s.";
        return String.format(template, field, message);
    }
}
