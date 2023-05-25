package hu.ulyssys.javaee.service.impl;

import hu.ulyssys.javaee.entity.Order;
import hu.ulyssys.javaee.service.OrderService;

import javax.ejb.Stateless;

@Stateless
public class OrderServiceImpl extends CoreServiceImpl<Order> implements OrderService {
}
