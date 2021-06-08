package br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.hibernate.HibernateDeleteClause;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.querydsl.jpa.hibernate.HibernateUpdateClause;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class HibernateDAO {
    private static final Logger log = LoggerFactory.getLogger(HibernateDAO.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return this.getSession().getCriteriaBuilder();
    }

    protected <T extends Serializable> JPQLQuery<T> newQuery() {
        return new HibernateQuery<>(this.getSession());
    }

    protected HibernateUpdateClause update(EntityPath<?> path) {
        return new HibernateQueryFactory(getSession()).update(path);
    }

    protected HibernateDeleteClause delete(EntityPath<?> path) {
        return new HibernateQueryFactory(getSession()).delete(path);
    }

    public void delete(Object object) {
        executarAcaoDelete(session -> {
            session.delete(object);
            flush();
        });
    }

    public <T extends TransferObject<? extends Serializable>> void delete(EntityPathBase<T> path, T entidade) {
        executarAcaoDelete(session -> new HibernateQueryFactory(session).delete(path).where(path.eq(entidade)).execute());
    }

    private void executarAcaoDelete(Consumer<Session> acaoDeletar) {
        try {
            acaoDeletar.accept(getSession());
        } catch (ConstraintViolationException cve) {
            log.info(cve.getMessage(), cve);
        }
    }

    public void evict(Object object) {
        this.getSession().evict(object);
    }

    public void clear() {
        this.getSession().clear();
    }

    public void saveOrUpdate(Object object) {
        this.getSession().saveOrUpdate(object);
    }

    public void save(Object object) {
        this.getSession().save(object);
    }

    public void refresh(Object object) {
        this.getSession().refresh(object);
    }

    public void flush() {
        this.getSession().flush();
    }
}
