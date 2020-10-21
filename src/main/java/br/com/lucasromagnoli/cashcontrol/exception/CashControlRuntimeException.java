package br.com.lucasromagnoli.cashcontrol.exception;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
public class CashControlRuntimeException extends RuntimeException {

    public CashControlRuntimeException() {}
    public CashControlRuntimeException(String message) {
        super(message);
    }
}
