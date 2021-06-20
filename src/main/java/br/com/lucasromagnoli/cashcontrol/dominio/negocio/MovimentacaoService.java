package br.com.lucasromagnoli.cashcontrol.dominio.negocio;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Movimentacao;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Parcelamento;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador.MovimentacaoValidacaoNegocio;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Service
@Transactional(readOnly = true)
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MovimentacaoValidacaoNegocio movimentacaoValidacaoNegocio;

    public Page<Movimentacao> listar(Pageable pageable) {
        return movimentacaoRepository.listar(pageable);
    }

    public Movimentacao consultarPeloId(Long id) {
        return Optional.ofNullable(movimentacaoRepository.pesquisaPorId(id)).orElseThrow(() -> new RegistroNaoEncontrado(Movimentacao.class, "id", id));
    }

    @Transactional(readOnly = false)
    public Movimentacao salvar(Movimentacao movimentacao) {
        movimentacaoValidacaoNegocio.validarCadastrar(movimentacao);
        movimentacaoRepository.save(movimentacao);
        return movimentacao;
    }

    @Transactional(readOnly = false)
    public Movimentacao atualizar(Movimentacao movimentacao) {
        movimentacaoValidacaoNegocio.validarAtualizar(movimentacao);
        movimentacaoRepository.atualizar(movimentacao);
        return movimentacao;
    }

    @Transactional(readOnly = false)
    public void remover(Long id) {
        Movimentacao movimentacao = new Movimentacao(id);
        movimentacaoValidacaoNegocio.validarRemover(movimentacao);
        movimentacaoRepository.remover(movimentacao);
    }

    @Transactional(readOnly = false)
    public void remover(Parcelamento parcelamento) {
        movimentacaoRepository.removerByParcelamento(parcelamento);
    }

    public boolean existeById(Movimentacao movimentacao) {
        return movimentacaoRepository.existe(movimentacao);
    }

    public boolean existeParcelamento(Movimentacao movimentacao) {
        return movimentacaoRepository.existeParcelamento(movimentacao);
    }

    public TipoMovimentacaoEnum getTipoMovimentacao(Movimentacao movimentacao) {
        return movimentacaoRepository.selectTipoMovimentacao(movimentacao);
    }
}
