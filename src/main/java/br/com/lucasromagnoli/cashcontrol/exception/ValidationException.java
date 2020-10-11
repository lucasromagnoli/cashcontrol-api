package br.com.lucasromagnoli.cashcontrol.exception;

import br.com.lucasromagnoli.cashcontrol.validator.ValidatorType;

public abstract class ValidationException extends CashControlRuntimeException {

    public abstract ValidatorType getValidatorType();
    public abstract String getHumanMessage();
}
