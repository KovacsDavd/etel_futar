package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.CartItem;
import hu.ulyssys.javaee.service.CartService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    List<CartItem> list;

    private void load() {
        if (cartService.getCartItems(loggedInUserMBean.getModel().getId()) != null) {
            list = cartService.getCartItems(loggedInUserMBean.getModel().getId());
        }
    }

    @PostConstruct
    private void init() {
        load();
    }


    public void removeFromCart(CartItem food) {
        cartService.removeFromCart(loggedInUserMBean.getModel().getId(), food);
        list.remove(food);
        load();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres eltávolítás", ""));
    }

    public int TotalPrice() {
        Long userId = loggedInUserMBean.getModel().getId();
        List<CartItem> list1 = cartService.getCartItems(userId);
        int sum = 0;
        for (CartItem cartItem : list1) {
            sum += cartItem.getFood().getPrice();
        }
        return sum;
    }

    public boolean isEmptyList() {
        Long userId = loggedInUserMBean.getModel().getId();
        return cartService.getCartItems(userId).isEmpty();
    }

    public LoggedInUserMBean getLoggedInUserMBean() {
        return loggedInUserMBean;
    }

    public void setLoggedInUserMBean(LoggedInUserMBean loggedInUserMBean) {
        this.loggedInUserMBean = loggedInUserMBean;
    }

    public List<CartItem> getList() {
        Long userId = loggedInUserMBean.getModel().getId();
        return cartService.getCartItems(userId);
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }
}
