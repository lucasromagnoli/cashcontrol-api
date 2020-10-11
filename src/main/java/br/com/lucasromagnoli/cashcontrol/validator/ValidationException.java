package br.com.lucasromagnoli.cashcontrol.validator;

import br.com.lucasromagnoli.cashcontrol.exception.CashControlRuntimeException;
import lombok.Getter;

import java.lang.reflect.Field;

public abstract class ValidationException extends CashControlRuntimeException {
    @Getter
    private ValidationMessage validationMessage;

    public ValidationException(String fieldName, String message, ValidatorType validatorType) {
        validationMessage = new ValidationMessage(fieldName, message, validatorType);
    }

    public ValidationException(Field field, String message, ValidatorType validatorType) {
        validationMessage = new ValidationMessage(field.getName(), message, validatorType);
    }
}
