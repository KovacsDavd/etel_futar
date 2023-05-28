package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.CartItem;
import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.service.CartService;
import hu.ulyssys.javaee.service.FoodService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CartMBean implements Serializable {
    @Inject
    private LoggedInUserMBean loggedInUserMBean;

    @Inject
    private CartService cartService;
    List<CartItem> list;

    private Food selectedFood;

    private void load() {
        list = cartService.getCartItems(loggedInUserMBean.getModel().getId());
    }

    @PostConstruct
    private void init() {
        load();
    }


    public void removeFromCart(CartItem food) {
        cartService.removeFromCart(loggedInUserMBean.getModel().getId(), food);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres eltávolítás", ""));
    }

    public void clearCart() {
        cartService.clearCart(loggedInUserMBean.getModel().getId());
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


    public List<CartItem> getList() {
        Long userId = loggedInUserMBean.getModel().getId();
        return cartService.getCartItems(userId);
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }
}
