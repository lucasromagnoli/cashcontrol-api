package br.com.lucasromagnoli.cashcontrol.dominio.negocio;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Movimentacao;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Parcelamento;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.PeriodicidadeEnum;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.ParcelamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Service
@Transactional(readOnly = true)
public class DespesaService {
    @Autowired
    private ParcelamentoRepository parcelamentoRepository;

    @Transactional(readOnly = false)
    public Parcelamento cadastrar(Parcelamento parcelamento) {
        // Validacões
        parcelamento.setMovimentacoes(gerarMovimentacoes(parcelamento));
        parcelamento.setValorTotal(parcelamento.getMovimentacoes()
                .stream()
                .map(Movimentacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        parcelamentoRepository.save(parcelamento);
        return parcelamento;
    }

    private List<Movimentacao> gerarMovimentacoes(Parcelamento parcelamento) {
        List<Movimentacao> movimentacoes = new LinkedList();
        for (int loop = 0; loop < parcelamento.getQuantidadeParcelas(); loop++) {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setValor(parcelamento.getValorParcela());
            movimentacao.setOrigem(parcelamento.getOrigem());
            movimentacao.setCategoria(parcelamento.getCategoria());
            movimentacao.setTipoMovimentacao(TipoMovimentacaoEnum.DESPESA);
            movimentacao.setData(movimentacoes.size() == 0
                    ? parcelamento.getDataPrimeiraParcela()
                    : PeriodicidadeEnum.incrementar(movimentacoes.get(movimentacoes.size() - 1).getData(), parcelamento.getPeriodicidade()));
            movimentacao.setParcelamento(parcelamento);
            movimentacoes.add(movimentacao);
        }

        return movimentacoes;
    }
}
