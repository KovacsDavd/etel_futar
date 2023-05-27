package hu.ulyssys.javaee.mbean.model;

import hu.ulyssys.javaee.entity.CartItem;
import hu.ulyssys.javaee.entity.UserRole;

import java.util.List;

public class LoggedInUserModel {
    private Long id;
    private String username;
    private UserRole role;
    private List<CartItem> cartItems;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
