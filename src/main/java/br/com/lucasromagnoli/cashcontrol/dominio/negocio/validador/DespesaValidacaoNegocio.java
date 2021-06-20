package br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.common.exception.ValidacaoException;
import br.com.lucasromagnoli.cashcontrol.common.i18n.Mensagem;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Categoria;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Parcelamento;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.CategoriaService;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.DespesaService;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.OrigemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant.Validacao.Movimentacao.MENSAGEM_MOVIMENTACAO_TIPO_DIFERENTE;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Component
public class DespesaValidacaoNegocio {
    @Autowired
    private DespesaService despesaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private OrigemService origemService;

    @Autowired
    private Mensagem mensagem;

    public void validarCadastrar(Parcelamento parcelamento) {
        if (!categoriaService.existeById(parcelamento.getCategoria())) {
            throw new RegistroNaoEncontrado(Categoria.class, "id", parcelamento.getCategoria().getId());
        }

        if (!origemService.existeById(parcelamento.getOrigem())) {
            throw new RegistroNaoEncontrado(Origem.class, "id", parcelamento.getOrigem().getId());
        }

        if (!categoriaService.pertenceAoGrupoComTipoDeMovimentacao(parcelamento.getCategoria(), TipoMovimentacaoEnum.DESPESA)) {
            throw new ValidacaoException(mensagem.get(MENSAGEM_MOVIMENTACAO_TIPO_DIFERENTE));
        }
    }

    public void validarRemover(Parcelamento parcelamento) {
        if (!despesaService.existe(parcelamento)) {
            throw new RegistroNaoEncontrado(Parcelamento.class, "id", parcelamento.getId());
        }
    }
}
