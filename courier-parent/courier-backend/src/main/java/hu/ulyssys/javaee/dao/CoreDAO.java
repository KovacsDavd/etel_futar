package hu.ulyssys.javaee.dao;

import hu.ulyssys.javaee.entity.CoreEntity;

import java.util.List;

public interface CoreDAO<T extends CoreEntity> {
    List<T> getAll();

    void add(T entity);

    void remove(Long id);

    T findById(Long id);

    void update(T entity);
}
