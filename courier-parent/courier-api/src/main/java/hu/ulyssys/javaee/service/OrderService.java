package hu.ulyssys.javaee.service;

import hu.ulyssys.javaee.entity.Order;

import java.util.List;

public interface OrderService extends CoreService<Order> {
    List<Order> getListByUserId(Long id);
}
