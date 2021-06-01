package br.com.lucasromagnoli.cashcontrol.common.exception;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class RegistroNaoEncontrado extends ValidacaoException {
    private final String campo;
    private final Object valor;
    private final String entidade;

    public RegistroNaoEncontrado(String entidade, String campo, Object valor) {
        this.campo = campo;
        this.valor = valor;
        this.entidade = entidade;
    }

    public RegistroNaoEncontrado(Class clazz, String campo, Object valor) {
        this.campo = campo;
        this.valor = valor;
        this.entidade = clazz.getSimpleName();
    }

    public String getCampo() {
        return campo;
    }

    public Object getValor() {
        return valor;
    }

    public String getEntidade() {
        return entidade;
    }
}
