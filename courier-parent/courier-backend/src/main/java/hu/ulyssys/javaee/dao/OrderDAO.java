package hu.ulyssys.javaee.dao;

import hu.ulyssys.javaee.entity.Order;

import java.util.List;

public interface OrderDAO extends CoreDAO<Order> {
    List<Order> getListByUserId(Long id);
}
