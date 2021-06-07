package br.com.lucasromagnoli.cashcontrol.dominio.persistencia;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
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
import static br.com.lucasromagnoli.cashcontrol.dominio.entidade.QOrigem.origem;
/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Repository
public class OrigemRepository extends GenericDAO<Origem, Long> {
    private JPQLQuery<Origem> queryOrigem(QBean<Origem> projections) {
        return newQuery().select(projections).from(origem);
    }

    private QBean<Origem> projecaoOrigem() {
        return Projections.fields(Origem.class,
                origem.id,
                origem.nome);
    }

    public Page<Origem> listar(Pageable pageable) {
        JPQLQuery<Origem> query = queryOrigem(projecaoOrigem());
        applyPagination(query, pageable, origem);
        QueryResults<Origem> origens = query.fetchResults();
        return new PageImpl<>(origens.getResults(), pageable, origens.getTotal());
    }

    public void atualizar(Origem origemAtualizar) {
        update(origem)
                .set(origem.nome, origemAtualizar.getNome())
                .where(origem.id.eq(origemAtualizar.getId()))
                .execute();
    }

    public void remover(Origem origemRemover) {
        delete(origem)
            .where(origem.id.eq(origemRemover.getId()))
            .execute();
    }

    public boolean existeByNome(Origem origemConsulta) {
        JPQLQuery<Origem> query = queryOrigem(Projections.fields(Origem.class, origem.id));
        QueryUtil.adicionarNe(query, origem.id, origemConsulta.getId());
        query.where(origem.nome.equalsIgnoreCase(origemConsulta.getNome()));
        return query.fetchFirst() != null;
    }

    public boolean existe(Origem origemConsulta) {
        return newQuery()
                .select(Projections.fields(Origem.class, origem.id))
                .from(origem)
                .where(origem.id.eq(origemConsulta.getId()))
                .fetchFirst() != null;
    }
}
