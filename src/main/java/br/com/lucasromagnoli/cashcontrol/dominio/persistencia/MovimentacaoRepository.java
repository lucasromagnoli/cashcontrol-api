package br.com.lucasromagnoli.cashcontrol.dominio.persistencia;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Movimentacao;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.GenericDAO;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static br.com.lucasromagnoli.cashcontrol.dominio.entidade.QMovimentacao.movimentacao;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Repository
public class MovimentacaoRepository extends GenericDAO<Movimentacao, Long> {
    private JPQLQuery<Movimentacao> queryMovimentacao(QBean<Movimentacao> projections) {
        return newQuery().select(projections).from(movimentacao);
    }

    private QBean<Movimentacao> projecaoMovimentacao() {
        return Projections.fields(Movimentacao.class,
                movimentacao.id,
                movimentacao.tipoMovimentacao,
                movimentacao.data,
                movimentacao.valor
                );
    }

    public Page<Movimentacao> listar(Pageable pageable) {
        JPQLQuery<Movimentacao> query = queryMovimentacao(projecaoMovimentacao());
        applyPagination(query, pageable, movimentacao);
        QueryResults<Movimentacao> movimentacoes = query.fetchResults();
        return new PageImpl<>(movimentacoes.getResults(), pageable, movimentacoes.getTotal());
    }
}
