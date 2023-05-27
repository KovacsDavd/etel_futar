package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.CartItem;
import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.service.CartService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartMBean implements Serializable {
    @Inject
    private LoggedInUserMBean loggedInUserMBean;

    @Inject
    private CartService cartService;

    private Food selectedFood;

    public void addToCart() {
        if (loggedInUserMBean.isLoggedIn()) {
            Long userId = loggedInUserMBean.getModel().getId();
            cartService.addToCart(userId, selectedFood);
            loggedInUserMBean.getModel().setCartItems(cartService.getCartItems(userId));
        }
    }

    public void removeFromCart() {
        if (loggedInUserMBean.isLoggedIn()) {
            Long userId = loggedInUserMBean.getModel().getId();
            cartService.removeFromCart(userId, selectedFood);
            loggedInUserMBean.getModel().setCartItems(cartService.getCartItems(userId));
        }
    }

    public void clearCart() {
        if (loggedInUserMBean.isLoggedIn()) {
            Long userId = loggedInUserMBean.getModel().getId();
            cartService.clearCart(userId);
            loggedInUserMBean.getModel().setCartItems(cartService.getCartItems(userId));
        }
    }

    public List<CartItem> getCartItems() {
        if (loggedInUserMBean.isLoggedIn()) {
            Long userId = loggedInUserMBean.getModel().getId();
            return cartService.getCartItems(userId);
        }
        return null;
    }

    public Food getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(Food selectedFood) {
        this.selectedFood = selectedFood;
    }

    public LoggedInUserMBean getLoggedInUserMBean() {
        return loggedInUserMBean;
    }

    public void setLoggedInUserMBean(LoggedInUserMBean loggedInUserMBean) {
        this.loggedInUserMBean = loggedInUserMBean;
    }

    public CartService getCartService() {
        return cartService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
