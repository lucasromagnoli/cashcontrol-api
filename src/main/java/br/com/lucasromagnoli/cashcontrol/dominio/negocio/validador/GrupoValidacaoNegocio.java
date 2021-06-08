package br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroDuplicado;
import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroRelacionamentoAtivo;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Categoria;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Grupo;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.CategoriaService;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Component
public class GrupoValidacaoNegocio {
    @Autowired
    private GrupoService grupoService;

    @Autowired
    private CategoriaService categoriaService;

    public void validarSalvar(Grupo grupo) {
        if (grupoService.existeComMesmoNome(grupo)) {
            throw new RegistroDuplicado(Grupo.class, "nome", grupo.getNome());
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

        if (categoriaService.existeByGrupo(grupo)) {
            throw new RegistroRelacionamentoAtivo(Categoria.class, Grupo.class);
        }
    }
}
