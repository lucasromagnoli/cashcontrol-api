package br.com.lucasromagnoli.cashcontrol.exception;

public class CashControlRuntimeException extends RuntimeException {

    public CashControlRuntimeException() {}
    public CashControlRuntimeException(String message) {
        super(message);
    }
}
