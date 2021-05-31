package br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.hibernate.HibernateQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

import static com.querydsl.core.types.Order.ASC;
import static com.querydsl.core.types.Order.DESC;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public abstract class GenericDAO<T extends TransferObject<K>, K extends Serializable> extends HibernateDAO {
    private static final Logger log = LoggerFactory.getLogger(GenericDAO.class);

    public Class<T> getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> getAll() {
        CriteriaQuery<T> criteria = this.getCriteriaBuilder().createQuery(getPersistentClass());
        Root<T> objeto = criteria.from(getPersistentClass());
        criteria.select(objeto);
        return getSession().createQuery(criteria).list();
    }

    public long count() {
        CriteriaQuery<Long> criteria = this.getCriteriaBuilder().createQuery(Long.class);
        criteria.select(this.getCriteriaBuilder().count(criteria.from(getPersistentClass())));
        return getSession().createQuery(criteria).getSingleResult();
    }

    public boolean tabelaPossuiRegistros() {
        CriteriaQuery<T> criteria = this.getCriteriaBuilder().createQuery(getPersistentClass());
        Root<T> objeto = criteria.from(getPersistentClass());
        criteria.multiselect(objeto.get("id"));
        criteria.select(objeto);
        return getSession().createQuery(criteria).setMaxResults(1).uniqueResult() != null;
    }

    public T pesquisaPorId(Long id) {
        return this.getSession().find(getPersistentClass(), id);
    }

    protected JPQLQuery<T> newQuery(Pageable pageable, EntityPathBase<T> entity) {
        JPQLQuery<T> query = new HibernateQuery<>(this.getSession());
        this.applyPagination(query, pageable, entity);
        return query;
    }

    public JPQLQuery<T> applyPagination(JPQLQuery<T> query, Pageable pageable, EntityPathBase<T> entity) {
        if (Objects.nonNull(pageable)) {
            query.offset(pageable.getOffset());
            if (pageable.getPageSize() > 0) {
                query.limit(pageable.getPageSize());
            }
            this.applySorting(pageable.getSort(), query, entity);
        }
        return query;
    }

    @SuppressWarnings("rawtypes")
    public JPQLQuery<T> applySorting(Sort sort, JPQLQuery<T> query, EntityPathBase<T> entity) {
        if (Objects.nonNull(sort)) {
            PathBuilder<T> builder = new PathBuilder<>(entity.getType(), entity.getMetadata());
            sort.forEach(o -> {
                Order order = o.isAscending() ? ASC : DESC;
                try {
                    query.orderBy(new OrderSpecifier(order, builder.get(o.getProperty())));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            });
        }
        return query;
    }
}
