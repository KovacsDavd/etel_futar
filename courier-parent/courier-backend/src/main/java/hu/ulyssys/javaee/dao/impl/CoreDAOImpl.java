package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.CoreDAO;
import hu.ulyssys.javaee.entity.CoreEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class CoreDAOImpl<T extends CoreEntity> implements CoreDAO<T> {

    @PersistenceContext(name = "CourierPersistence")
    EntityManager entityManager;

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("select n from " + getManagedClass().getSimpleName() + " n", getManagedClass()).getResultList();
    }

    @Override
    public void add(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public T findById(Long id) {
        return entityManager.find(getManagedClass(), id);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    protected abstract Class<T> getManagedClass();
}
