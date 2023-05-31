package hu.ulyssys.javaee.service.impl;

import hu.ulyssys.javaee.dao.OrderDAO;
import hu.ulyssys.javaee.entity.Order;
import hu.ulyssys.javaee.service.OrderService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class OrderServiceImpl extends CoreServiceImpl<Order> implements OrderService {
    @Inject
    private OrderDAO orderDAO;
    @Override
    public List<Order> getListByUserId(Long id) {
        return orderDAO.getListByUserId(id);
    }
}
