package br.com.lucasromagnoli.cashcontrol.common.exception;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class RegistroDuplicado extends ValidacaoException {
    public RegistroDuplicado(Class clazz, String campo, Object valor) {
        super(clazz, campo, valor);
    }
}
