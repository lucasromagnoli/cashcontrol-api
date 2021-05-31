package br.com.lucasromagnoli.cashcontrol.common.exception;

import java.util.Map;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class RegistroDuplicado extends ValidacaoException {
    public RegistroDuplicado(String campo, String campoDescricao, String descricao) {
        super(Map.of(campo, campoDescricao), descricao);
    }
}
