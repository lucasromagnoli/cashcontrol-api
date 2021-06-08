package br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroDuplicado;
import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
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
public class CategoriaValidacaoNegocio {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private GrupoService grupoService;

    public void validarSalvar(Categoria categoria) {
        if (categoriaService.existeComMesmoNome(categoria)) {
            throw new RegistroDuplicado(Categoria.class, "nome", categoria.getNome());
        }

        if (!grupoService.existeById(categoria.getGrupo())) {
            throw new RegistroNaoEncontrado(Grupo.class, "id", categoria.getGrupo().getId());
        }
    }

    public void validarAtualizar(Categoria categoria) {
        if (!categoriaService.existeById(categoria)) {
            throw new RegistroNaoEncontrado(Categoria.class, "id", categoria.getId());
        }

        validarSalvar(categoria);
    }

    public void validarRemover(Categoria categoria) {
        if (!categoriaService.existeById(categoria)) {
            throw new RegistroNaoEncontrado(Categoria.class, "id", categoria.getId());
        }
    }
}
