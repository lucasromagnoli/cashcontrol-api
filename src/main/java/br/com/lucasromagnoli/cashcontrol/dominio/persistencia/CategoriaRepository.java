package br.com.lucasromagnoli.cashcontrol.dominio.persistencia;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Categoria;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Grupo;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.GenericDAO;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.QueryUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static br.com.lucasromagnoli.cashcontrol.dominio.entidade.QCategoria.categoria;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Repository
public class CategoriaRepository extends GenericDAO<Categoria, Long> {
    private JPQLQuery<Categoria> queryCategoria(Expression<Categoria> projections) {
        return newQuery().select(projections).from(categoria);
    }

    private Expression<Categoria> projecaoCategoria() {
        return Projections.fields(categoria,
                categoria.id,
                categoria.nome,
                categoria.descricao,
                QueryUtil.bean(categoria.grupo,
                        categoria.grupo.id,
                        categoria.grupo.nome,
                        categoria.grupo.tipoMovimentacao)
        );
    }

    public Page<Categoria> listar(Pageable pageable) {
        JPQLQuery<Categoria> query = queryCategoria(projecaoCategoria());
        applyPagination(query, pageable, categoria);
        QueryResults<Categoria> categorias = query.fetchResults();
        return new PageImpl<>(categorias.getResults(), pageable, categorias.getTotal());
    }

    public void atualizar(Categoria categoriaAtualizar) {
        update(categoria)
                .set(categoria.nome, categoriaAtualizar.getNome())
                .set(categoria.descricao, categoriaAtualizar.getDescricao())
                .set(categoria.grupo.id, categoriaAtualizar.getGrupo().getId())
                .where(categoria.id.eq(categoriaAtualizar.getId()))
                .execute();
    }

    public void remover(Categoria categoriaRemover) {
        delete(categoria)
                .where(categoria.id.eq(categoriaRemover.getId()))
                .execute();
    }

    public boolean existeByNome(Categoria categoriaConsulta) {
        JPQLQuery<Categoria> query = queryCategoria(Projections.fields(Categoria.class, categoria.id));
        QueryUtil.adicionarNe(query, categoria.id, categoriaConsulta.getId());
        QueryUtil.adicionarEq(query, categoria.grupo.id, categoriaConsulta.getGrupo().getId());
        query.where(categoria.nome.equalsIgnoreCase(categoriaConsulta.getNome()));
        return query.fetchFirst() != null;
    }

    public boolean existe(Categoria categoriaConsulta) {
        return newQuery()
                .select(Projections.fields(Categoria.class, categoria.id))
                .from(categoria)
                .where(categoria.id.eq(categoriaConsulta.getId()))
                .fetchFirst() != null;
    }

    public boolean existeByGrupoId(Grupo grupo) {
        return queryCategoria(Projections.fields(Categoria.class, categoria.id))
                .where(categoria.grupo.id.eq(grupo.getId()))
                .fetchFirst() != null;
    }
}
