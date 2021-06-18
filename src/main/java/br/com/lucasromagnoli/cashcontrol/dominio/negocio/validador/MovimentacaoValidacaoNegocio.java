package br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.common.exception.ValidacaoException;
import br.com.lucasromagnoli.cashcontrol.common.i18n.Mensagem;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Categoria;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Movimentacao;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.CategoriaService;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.MovimentacaoService;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.OrigemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.Movimentacao.MENSAGEM_MOVIMENTACAO_TIPO_DIFERENTE;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Component
public class MovimentacaoValidacaoNegocio {
    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private OrigemService origemService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private Mensagem mensagem;

    public void validarCadastrar(Movimentacao movimentacao) {
        if (!categoriaService.existeById(movimentacao.getCategoria())) {
            throw new RegistroNaoEncontrado(Categoria.class, "id", movimentacao.getCategoria().getId());
        }

        if (!origemService.existeById(movimentacao.getOrigem())) {
            throw new RegistroNaoEncontrado(Origem.class, "id", movimentacao.getOrigem().getId());
        }

        if (!categoriaService.pertenceAoGrupoComTipoDeMovimentacao(movimentacao.getCategoria(), movimentacao.getTipoMovimentacao())) {
            throw new ValidacaoException(mensagem.get(MENSAGEM_MOVIMENTACAO_TIPO_DIFERENTE));
        }
    }

    public void validarAtualizar(Movimentacao movimentacao) {
        if (!movimentacaoService.existeById(movimentacao)) {
            throw new RegistroNaoEncontrado(Movimentacao.class, "id", movimentacao.getId());
        }

        if (Objects.nonNull(movimentacao.getCategoria()) && !categoriaService.existeById(movimentacao.getCategoria())) {
            throw new RegistroNaoEncontrado(Categoria.class, "id", movimentacao.getCategoria().getId());
        }

        if (Objects.nonNull(movimentacao.getOrigem()) && !origemService.existeById(movimentacao.getOrigem())) {
            throw new RegistroNaoEncontrado(Origem.class, "id", movimentacao.getOrigem().getId());
        }
    }

    public void validarRemover(Movimentacao movimentacao) {
        if (!movimentacaoService.existeById(movimentacao)) {
            throw new RegistroNaoEncontrado(Movimentacao.class, "id", movimentacao.getId());
        }
    }
}
