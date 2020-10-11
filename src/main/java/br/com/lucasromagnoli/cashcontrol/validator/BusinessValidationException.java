package br.com.lucasromagnoli.cashcontrol.validator;

import java.lang.reflect.Field;

public class BusinessValidationException extends ValidationException {
    public BusinessValidationException(String fieldName, String message) {
        super(fieldName, message, ValidatorType.BUSINESS);
    }

    public BusinessValidationException(Field field, String message) {
        super(field, message, ValidatorType.BUSINESS);
    }
}
