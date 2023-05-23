package hu.ulyssys.javaee.service;

import hu.ulyssys.javaee.entity.CoreEntity;

import java.util.List;

public interface CoreService<T extends CoreEntity> {
    List<T> getAll();

    void add(T entity);

    void update(T entity);

    void remove(T entity);

    T findById(Long id);
}
