package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.entity.UserRole;
import hu.ulyssys.javaee.mbean.model.LoggedInUserModel;
import hu.ulyssys.javaee.service.CartService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoggedInUserMBean implements Serializable {
    private LoggedInUserModel model;
    @Inject
    private CartService cartService;

    /*@PostConstruct
    public void init() {
        if (isLoggedIn()) {
            Long userId = model.getId();
            cartService.getOrCreateCart(userId);
        }
    }*/
    public void deleteCart() {
        Long userId = model.getId();
        cartService.deleteCart(userId);
    }

    public boolean isLoggedIn() {
        return model != null;
    }

    public boolean isAdmin() {
        return model.getRole().equals(UserRole.ADMIN);
    }

    public LoggedInUserModel getModel() {
        return model;
    }

    public void setModel(LoggedInUserModel model) {
        this.model = model;
    }


}
