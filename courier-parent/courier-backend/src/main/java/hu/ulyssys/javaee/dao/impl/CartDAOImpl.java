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
public class CartDAOImpl implements CartDAO {
    @PersistenceContext(name = "CourierPersistence")
    EntityManager entityManager;


    @Override
    public void delete(Long id) {
        //entityManager.remove(entityManager.find(Cart.class,id));
        //entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        Cart cart = findByUserId(id);
        if (cart != null) {
            List<CartItem> cartItems = cart.getItems();
            for (CartItem cartItem : cartItems) {
                entityManager.remove(cartItem);
            }
            entityManager.remove(cart);
        }
    }

    @Override
    public void add(Cart entity) {
        entityManager.persist(entity);
    }

    @Override
    public Cart findByUserId(Long id) {
        //return entityManager.find(Cart.class, id);
        TypedQuery<Cart> query = entityManager.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class);
        query.setParameter("userId", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void update(Cart entity) {
        entityManager.merge(entity);
    }
}
