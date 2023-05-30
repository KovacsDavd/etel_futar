package hu.ulyssys.javaee.service.impl;

import hu.ulyssys.javaee.dao.CartDAO;
import hu.ulyssys.javaee.dao.CartItemDAO;
import hu.ulyssys.javaee.dao.CoreDAO;
import hu.ulyssys.javaee.entity.Cart;
import hu.ulyssys.javaee.entity.CartItem;
import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.service.CartService;
import hu.ulyssys.javaee.service.UserService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CartServiceImpl implements CartService {
    @Inject
    private CartDAO cartDAO;

    @Inject
    private UserService userService;

    @Inject
    private CartItemDAO cartItemDAO;

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Cart getOrCreateCart(Long userId) {
        Cart cart = cartDAO.findByUserId(userId);

        if (cart == null) {
            cart = new Cart();
            User user = userService.findById(userId);
            cart.setUser(user);
            cartDAO.add(cart);
        }

        return cart;
    }
    @Override
    public Cart getCart(Long userId) {
        Cart cart = cartDAO.findByUserId(userId);
        return cart;
    }
    @Override
    public void addToCart(Long userId, Food food) {
        Cart cart = getOrCreateCart(userId);
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setFood(food);
        cart.getItems().add(newCartItem);
        cartDAO.update(cart);
    }

    @Override
    public void removeFromCart(Long userId, CartItem food) {
        Cart cart = getOrCreateCart(userId);
        CartItem cartItem = findCartItemByFood(cart, food);
        if (cartItem != null) {
            cart.getItems().remove(cartItem);
            cartItemDAO.remove(cartItem.getId());
            cartDAO.update(cart);
        }
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void clearCart(Long userId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().clear();
        cartDAO.update(cart);
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public List<CartItem> getCartItems(Long userId) {
        Cart cart = getOrCreateCart(userId);
        return cart.getItems();
    }
    @Override
    public void deleteCart(Long id) {
        cartDAO.remove(id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public CartItem findCartItemByFood(Cart cart, CartItem food) {
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().getId()==food.getFood().getId()) {
                return cartItem;
            }
        }
        return null;
    }
}
