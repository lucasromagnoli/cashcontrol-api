package br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroDuplicado;
import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.OrigemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Component
public class OrigemValidacaoNegocio {
    @Autowired
    private OrigemService origemService;

    public void validarSalvar(Origem origem) {
        if (origemService.existeComMesmoNome(origem)) {
            throw new RegistroDuplicado(Origem.class, "nome", origem.getNome());
        }
    }

    public void validarAtualizar(Origem origem) {
        if (!origemService.existeById(origem)) {
            throw new RegistroNaoEncontrado(Origem.class, "id", origem.getId());
        }

        validarSalvar(origem);
    }

    public void validarRemover(Origem origem) {
        if (!origemService.existeById(origem)) {
            throw new RegistroNaoEncontrado(Origem.class, "id", origem.getId());
        }
    }
}
