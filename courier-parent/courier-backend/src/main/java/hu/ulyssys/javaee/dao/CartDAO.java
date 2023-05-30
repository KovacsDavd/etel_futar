package hu.ulyssys.javaee.dao;

import hu.ulyssys.javaee.entity.Cart;
import hu.ulyssys.javaee.entity.CartItem;

import java.util.List;

public interface CartDAO extends CoreDAO<Cart>{
    Cart findByUserId(Long id);
}
