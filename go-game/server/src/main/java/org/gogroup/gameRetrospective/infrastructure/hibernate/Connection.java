package org.gogroup.gameRetrospective.infrastructure.hibernate;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.gogroup.gameRetrospective.domain.Move;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Connection {
    private Session session;

    public Connection() {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").addAnnotatedClass(Move.class)
                .buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

    public void save(Object entity) {
        Transaction transaction = this.session.beginTransaction();
        this.session.persist(entity);
        this.session.flush();
        transaction.commit();
    }

    public Query executeCriteriaQuery(CriteriaQuery<?> criteriaQuery) {
        return this.session.createQuery(criteriaQuery);
    }

    public Object executeStatement(String statement) {
        Query query = this.session.createQuery(statement);
        return query.getResultList();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return this.session.getCriteriaBuilder();
    }
}
