package com.project.dao;

import com.project.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public abstract class GenericDao<T> {

    private final Class<T> entityClass;

    protected GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    // ============================
    // CRUD
    // ============================

    public void save(T entity) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void update(T entity) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void delete(T entity) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.remove(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    // ============================
    // CONSULTAS
    // ============================

    public T findById(Object id) {
        try (Session session = getSession()) {
            return session.get(entityClass, id);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        try (Session session = getSession()) {
            return session
                    .createQuery("FROM " + entityClass.getSimpleName())
                    .list();
        }
    }
}

