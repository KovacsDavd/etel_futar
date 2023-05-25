package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.OrderDAO;
import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.entity.Order;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class OrderDAOImpl extends CoreDAOImpl<Order> implements OrderDAO {

    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }
}
