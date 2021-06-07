package br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroDuplicado;
import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.common.i18n.Mensagem;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Grupo;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.GrupoService;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.OrigemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_REGISTRO_DUPLICADO;
import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_VERIFIQUE_CAMPOS;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Component
public class GrupoValidacaoNegocio {
    @Autowired
    private GrupoService grupoService;

    @Autowired
    private Mensagem mensagem;

    public void validarSalvar(Grupo grupo) {
        if (grupoService.existeComMesmoNome(grupo)) {
            throw new RegistroDuplicado("nome",
                    mensagem.get(MENSAGEM_REGISTRO_DUPLICADO, grupo.getNome()),
                    mensagem.get(MENSAGEM_VERIFIQUE_CAMPOS)
            );
        }
    }

    public void validarAtualizar(Grupo grupo) {
        if (!grupoService.existeById(grupo)) {
            throw new RegistroNaoEncontrado(Grupo.class, "id", grupo.getId());
        }

        validarSalvar(grupo);
    }

    public void validarRemover(Grupo grupo) {
        if (!grupoService.existeById(grupo)) {
            throw new RegistroNaoEncontrado(Grupo.class, "id", grupo.getId());
        }
    }
}
