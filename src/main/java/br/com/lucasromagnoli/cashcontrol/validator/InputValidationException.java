package br.com.lucasromagnoli.cashcontrol.validator;

import java.lang.reflect.Field;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
public class InputValidationException extends ValidationException {
    public InputValidationException(String fieldName, String message) {
        super(fieldName, message, ValidatorType.INPUT);
    }

    public InputValidationException(Field field, String message) {
        super(field, message, ValidatorType.INPUT);
    }
}
