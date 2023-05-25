package hu.ulyssys.javaee.service.impl;

import hu.ulyssys.javaee.dao.OrderFoodDAO;
import hu.ulyssys.javaee.entity.OrderFood;
import hu.ulyssys.javaee.service.OrderFoodService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class OrderFoodServiceImpl implements OrderFoodService {
    @Inject
    private OrderFoodDAO orderFoodDAO;
    @Override
    public void add(OrderFood orderFood) {
        orderFoodDAO.add(orderFood);
    }
}
