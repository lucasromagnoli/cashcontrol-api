package br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroDuplicado;
import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.common.i18n.Mensagem;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.OrigemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_REGISTRO_DUPLICADO;
import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_VERIFIQUE_CAMPOS;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Component
public class OrigemValidacaoNegocio {
    @Autowired
    private OrigemService origemService;

    @Autowired
    private Mensagem mensagem;

    public void validarSalvar(Origem origem) {
        if (origemService.existeComMesmoNome(origem)) {
            throw new RegistroDuplicado("nome",
                    mensagem.get(MENSAGEM_REGISTRO_DUPLICADO, origem.getNome()),
                    mensagem.get(MENSAGEM_VERIFIQUE_CAMPOS)
            );
        }
    }

    public void validarRemover(Origem origem) {
        if (!origemService.existeById(origem)) {
            throw new RegistroNaoEncontrado(Origem.class, "id", origem.getId());
        }
    }
}
