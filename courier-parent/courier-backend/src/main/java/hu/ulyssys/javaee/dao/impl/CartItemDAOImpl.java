package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.CartItemDAO;
import hu.ulyssys.javaee.entity.CartItem;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CartItemDAOImpl extends CoreDAOImpl<CartItem> implements CartItemDAO {

    @Override
    protected Class<CartItem> getManagedClass() {
        return CartItem.class;
    }
}
