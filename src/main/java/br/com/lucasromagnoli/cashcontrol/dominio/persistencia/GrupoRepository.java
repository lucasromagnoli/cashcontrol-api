package br.com.lucasromagnoli.cashcontrol.dominio.persistencia;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Grupo;
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

import static br.com.lucasromagnoli.cashcontrol.dominio.entidade.QGrupo.grupo;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Repository
public class GrupoRepository extends GenericDAO<Grupo, Long> {
    private JPQLQuery<Grupo> queryGrupo(QBean<Grupo> projections) {
        return newQuery().select(projections).from(grupo);
    }

    private QBean<Grupo> projecaoGrupo() {
        return Projections.fields(Grupo.class,
                grupo.id,
                grupo.nome,
                grupo.tipoMovimentacao);

    }

    public Page<Grupo> listar(Pageable pageable) {
        JPQLQuery<Grupo> query = queryGrupo(projecaoGrupo());
        applyPagination(query, pageable, grupo);
        QueryResults<Grupo> grupos = query.fetchResults();
        return new PageImpl<>(grupos.getResults(), pageable, grupos.getTotal());
    }

    public void atualizar(Grupo grupoAtualizar) {
        update(grupo)
                .set(grupo.nome, grupoAtualizar.getNome())
                .set(grupo.tipoMovimentacao, grupoAtualizar.getTipoMovimentacao())
                .where(grupo.id.eq(grupoAtualizar.getId()))
                .execute();
    }

    public void remover(Grupo grupoRemover) {
        delete(grupo)
                .where(grupo.id.eq(grupoRemover.getId()))
                .execute();
    }

    public boolean existeByNome(Grupo grupoConsulta) {
        JPQLQuery<Grupo> query = queryGrupo(Projections.fields(Grupo.class, grupo.id));
        QueryUtil.adicionarNe(query, grupo.id, grupoConsulta.getId());
        query.where(grupo.nome.equalsIgnoreCase(grupoConsulta.getNome()));
        return query.fetchFirst() != null;
    }

    public boolean existe(Grupo grupoConsulta) {
        return newQuery()
                .select(Projections.fields(Grupo.class, grupo.id))
                .from(grupo)
                .where(grupo.id.eq(grupoConsulta.getId()))
                .fetchFirst() != null;
    }
}
