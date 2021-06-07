package br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroDuplicado;
import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.common.i18n.Mensagem;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Categoria;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.CategoriaService;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_REGISTRO_DUPLICADO;
import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.MENSAGEM_VERIFIQUE_CAMPOS;

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

    @Autowired
    private Mensagem mensagem;

    public void validarSalvar(Categoria categoria) {
        if (categoriaService.existeComMesmoNome(categoria)) {
            throw new RegistroDuplicado("nome",
                    mensagem.get(MENSAGEM_REGISTRO_DUPLICADO, categoria.getNome()),
                    mensagem.get(MENSAGEM_VERIFIQUE_CAMPOS)
            );
        }

        if (!grupoService.existeById(categoria.getGrupo())) {
            throw new RegistroNaoEncontrado(Categoria.class, "grupo.id", categoria.getGrupo().getId());
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
