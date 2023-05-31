package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.OrderDAO;
import hu.ulyssys.javaee.entity.Order;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class OrderDAOImpl extends CoreDAOImpl<Order> implements OrderDAO {

    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }

    @Override
    public List<Order> getListByUserId(Long id) {
        TypedQuery<Order> query = getEntityManager().createQuery("select o from Order o where o.creatorUser.id = :id", Order.class);
        query.setParameter("id", id);

        List<Order> orderList = query.getResultList();
        return orderList;
    }
}
