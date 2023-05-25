package hu.ulyssys.javaee.service.impl;

import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.entity.Order;
import hu.ulyssys.javaee.service.OrderService;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrderServiceImpl extends CoreServiceImpl<Order> implements OrderService {
    @Override
    public List<Food> getFoodsByOrderId(Long orderId) {
        return null;
    }
}
