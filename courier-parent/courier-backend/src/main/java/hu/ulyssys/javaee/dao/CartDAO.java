package hu.ulyssys.javaee.dao;

import hu.ulyssys.javaee.entity.Cart;

public interface CartDAO extends CoreDAO<Cart>{
    Cart findByUserId(Long id);
}
