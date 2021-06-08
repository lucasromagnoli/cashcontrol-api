package br.com.lucasromagnoli.cashcontrol.common.exception;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class RegistroRelacionamentoAtivo extends ValidacaoException {
    public RegistroRelacionamentoAtivo(Class clazzPai, Class clazzFilho) {
        super();
        this.setEntidade(clazzPai.getSimpleName());
        this.setValor(clazzFilho.getSimpleName());
    }
}
