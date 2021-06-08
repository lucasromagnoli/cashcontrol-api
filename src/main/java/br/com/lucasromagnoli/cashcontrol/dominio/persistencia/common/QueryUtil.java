package br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common;

import com.google.common.collect.Lists;
import com.querydsl.core.FetchableQuery;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.hibernate.HibernateUpdateClause;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;


/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class QueryUtil {
    private static final String PERCENT = "%";

    public static <T> void adicionarIn(JPQLQuery<?> query, SimpleExpression<T> expression, Collection<? extends T> items) {
        if (CollectionUtils.isNotEmpty(items)) {
            query.where(criarExpressaoInOuNotIn(expression, items, true));
        }
    }

    private static <T> BooleanExpression criarExpressaoInOuNotIn(SimpleExpression<T> expression, Collection<? extends T> items, boolean expressaoIn) {
        List<T> list = Lists.newArrayList(items);
        if (items.size() == 1) {
            return expressaoIn ? expression.eq(list.get(0)) : expression.ne(list.get(0));
        }
        return expressaoIn ? expression.in(list) : expression.notIn(list);
    }

    public static <T> void adicionarNotIn(JPQLQuery<?> query, SimpleExpression<T> expression, Collection<? extends T> items) {
        if (CollectionUtils.isNotEmpty(items)) {
            query.where(criarExpressaoInOuNotIn(expression, items, false));
        }
    }

    public static <T> void adicionarEq(FetchableQuery<?, ?> query, SimpleExpression<T> expression, T valor) {
        if (!(valor == null || StringUtils.isBlank(valor.toString()))) {
            query.where(expression.eq(valor));
        }
    }

    public static <T> void adicionarNe(FetchableQuery<?, ?> query, SimpleExpression<T> expression, T valor) {
        if (!(valor == null || StringUtils.isBlank(valor.toString()))) {
            query.where(expression.ne(valor));
        }
    }

    public static void adicionarLike(JPQLQuery<?> query, StringExpression expression, String valor) {
        if (StringUtils.isNotBlank(valor)) {
            query.where(expression.toUpperCase().like(PERCENT.concat(valor.toUpperCase()).concat(PERCENT)));
        }
    }

    public static void adicionarLikeIgnoreCase(JPQLQuery<?> query, StringExpression expression, String valor) {
        if (StringUtils.isNotBlank(valor)) {
            query.where(expression.toUpperCase().likeIgnoreCase(PERCENT.concat(valor.toUpperCase()).concat(PERCENT)));
        }
    }

    public static <T extends TransferObject<?>> void adicionarUpdate(HibernateUpdateClause query, EntityPathBase<T> entityPath, T parametro) {
        T entidade = parametro != null && parametro.isPersistido() ? parametro : null;
        query.set(entityPath, entidade);
    }

    public static <T extends TransferObject<?>> Expression<T> as(Expression<T> expression, Path<T> entityPath) {
        return ExpressionUtils.operation(entityPath.getType(), Ops.ALIAS, expression, entityPath);
    }

    public static <T extends TransferObject<?>> Expression<T> bean(Path<T> entityPath, Expression<?>... exprs) {
        QBean<T> qBean = Projections.fields(entityPath.getType(), exprs);
        return as(qBean.skipNulls(), entityPath);
    }
}
