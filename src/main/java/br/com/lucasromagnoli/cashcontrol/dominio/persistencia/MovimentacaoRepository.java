package br.com.lucasromagnoli.cashcontrol.dominio.persistencia;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Movimentacao;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.GenericDAO;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.QueryUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

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
                movimentacao.valor,
                QueryUtil.bean(movimentacao.categoria,
                        movimentacao.categoria.id,
                        movimentacao.categoria.nome),
                QueryUtil.bean(movimentacao.origem,
                        movimentacao.origem.id,
                        movimentacao.origem.nome));
    }

    public Page<Movimentacao> listar(Pageable pageable) {
        JPQLQuery<Movimentacao> query = queryMovimentacao(projecaoMovimentacao());
        applyPagination(query, pageable, movimentacao);
        QueryResults<Movimentacao> movimentacoes = query.fetchResults();
        return new PageImpl<>(movimentacoes.getResults(), pageable, movimentacoes.getTotal());
    }

    public void atualizar(Movimentacao movimentacaoAtualizar) {
        var query = update(movimentacao);
        QueryUtil.adicionarSet(query, movimentacao.valor, movimentacaoAtualizar.getValor());
        QueryUtil.adicionarSet(query, movimentacao.data, movimentacaoAtualizar.getData());
        QueryUtil.adicionarSet(query, movimentacao.categoria, movimentacaoAtualizar.getCategoria());
        QueryUtil.adicionarSet(query, movimentacao.origem, movimentacaoAtualizar.getOrigem());
        query.where(movimentacao.id.eq(movimentacaoAtualizar.getId()))
                .execute();
    }

    public void remover(Movimentacao movimentacaoRemover) {
        delete(movimentacao)
                .where(movimentacao.id.eq(movimentacaoRemover.getId()))
                .execute();
    }

    public boolean existe(Movimentacao movimentacaoConsulta) {
        return Objects.nonNull(newQuery()
                .select(Projections.fields(Movimentacao.class, movimentacao.id))
                .from(movimentacao)
                .where(movimentacao.id.eq(movimentacaoConsulta.getId()))
                .fetchFirst());
    }

    public TipoMovimentacaoEnum selectTipoMovimentacao(Movimentacao movimentacaoConsulta) {
        Movimentacao queryRetorno = newQuery()
                .select(Projections.fields(Movimentacao.class, movimentacao.tipoMovimentacao))
                .from(movimentacao)
                .where(movimentacao.id.eq(movimentacaoConsulta.getId()))
                .fetchFirst();

        return Optional.ofNullable(queryRetorno.getTipoMovimentacao()).orElse(null);
    }
}
