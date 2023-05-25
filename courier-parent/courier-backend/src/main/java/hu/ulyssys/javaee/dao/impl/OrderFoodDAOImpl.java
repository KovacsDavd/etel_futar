package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.OrderFoodDAO;
import hu.ulyssys.javaee.entity.OrderFood;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderFoodDAOImpl implements OrderFoodDAO {
    @PersistenceContext(name = "CourierPersistence")
    EntityManager entityManager;
    @Override
    public void add(OrderFood orderFood) {
        entityManager.persist(orderFood);
    }
}
