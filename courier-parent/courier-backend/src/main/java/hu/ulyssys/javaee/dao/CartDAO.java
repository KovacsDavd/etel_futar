package hu.ulyssys.javaee.dao;

import hu.ulyssys.javaee.entity.Cart;
import hu.ulyssys.javaee.entity.CartItem;

import java.util.List;

public interface CartDAO {
    void delete(Long id);

    void add(Cart entity);

    Cart findByUserId(Long id);

    void update(Cart entity);
}
