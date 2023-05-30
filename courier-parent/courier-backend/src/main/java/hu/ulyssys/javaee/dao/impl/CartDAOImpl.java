package hu.ulyssys.javaee.dao.impl;

import hu.ulyssys.javaee.dao.CartDAO;
import hu.ulyssys.javaee.entity.Cart;
import hu.ulyssys.javaee.entity.CartItem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CartDAOImpl extends CoreDAOImpl<Cart> implements CartDAO {
    @PersistenceContext(name = "CourierPersistence")
    EntityManager entityManager;


    @Override
    public void remove(Long id) {
        Cart cart = findByUserId(id);
        entityManager.remove(cart);
    }

    @Override
    protected Class<Cart> getManagedClass() {
        return Cart.class;
    }

    @Override
    public Cart findByUserId(Long id) {
        TypedQuery<Cart> query = entityManager.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class);
        query.setParameter("userId", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
