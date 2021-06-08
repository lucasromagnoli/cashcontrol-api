package br.com.lucasromagnoli.cashcontrol.common.exception;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class CashControlRuntimeException extends RuntimeException {
    private String descricao;

    public CashControlRuntimeException() {
    }

    public CashControlRuntimeException(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
