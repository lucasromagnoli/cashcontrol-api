package br.com.lucasromagnoli.cashcontrol.common.exception;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class NegocioException extends CashControlRuntimeException {
    public NegocioException() {
    }

    public NegocioException(String descricao) {
        super(descricao);
    }
}
