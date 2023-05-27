package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.CartItemDAO;
import hu.ulyssys.javaee.entity.CartItem;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CartItemDAOImpl implements CartItemDAO {
    @PersistenceContext(name = "CourierPersistence")
    EntityManager entityManager;

    @Override
    public void add(CartItem cartItem) {
        entityManager.persist(cartItem);
    }

    @Override
    public void update(CartItem cartItem) {
        entityManager.merge(cartItem);
    }

    @Override
    public void remove(CartItem cartItem) {
        entityManager.remove(entityManager.contains(cartItem) ? cartItem : entityManager.merge(cartItem));
    }
}
