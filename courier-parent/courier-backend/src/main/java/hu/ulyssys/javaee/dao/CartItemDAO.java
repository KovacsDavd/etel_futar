package hu.ulyssys.javaee.dao;

import hu.ulyssys.javaee.entity.CartItem;

public interface CartItemDAO {
    void add(CartItem cartItem);
    void update(CartItem cartItem);
    void remove(CartItem cartItem);
}
