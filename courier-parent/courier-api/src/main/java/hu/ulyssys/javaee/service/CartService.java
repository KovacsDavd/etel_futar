package hu.ulyssys.javaee.service;

import hu.ulyssys.javaee.entity.Cart;
import hu.ulyssys.javaee.entity.CartItem;
import hu.ulyssys.javaee.entity.Food;

import java.util.List;

public interface CartService {
    Cart getCart(Long userId);
    void addToCart(Long userId, Food food);
    void removeFromCart(Long userId, CartItem food);
    void clearCart(Long userId);
    CartItem findCartItemByFood(Cart cart, CartItem food);
    Cart getOrCreateCart(Long userId);
    List<CartItem> getCartItems(Long userId);
    void deleteCart(Long id);
}
