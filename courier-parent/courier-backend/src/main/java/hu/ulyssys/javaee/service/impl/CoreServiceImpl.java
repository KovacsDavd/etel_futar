package hu.ulyssys.javaee.service.impl;

import hu.ulyssys.javaee.dao.CoreDAO;
import hu.ulyssys.javaee.entity.CoreEntity;
import hu.ulyssys.javaee.service.CoreService;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

public abstract class CoreServiceImpl<T extends CoreEntity> implements CoreService<T> {
    @Inject
    private CoreDAO<T> entityDAO;

    @TransactionAttribute(TransactionAttributeType.NEVER)
    @Override
    public List<T> getAll() {
        return entityDAO.getAll();
    }

    @Override
    public void add(T entity) {
        entityDAO.add(entity);
    }

    @Override
    public void update(T entity) {
        entityDAO.update(entity);
    }

    @Override
    public void remove(T entity) {
        entityDAO.remove(entity.getId());
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public T findById(Long id) {
        return entityDAO.findById(id);
    }
}
