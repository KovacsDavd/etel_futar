package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.mbean.model.LoggedInUserModel;
import hu.ulyssys.javaee.mbean.model.LoginModel;
import hu.ulyssys.javaee.service.CartService;
import hu.ulyssys.javaee.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginRequestMBean {
    @Inject
    private LoggedInUserMBean bean;
    private LoginModel model;

    @PostConstruct
    private void init() {
        model = new LoginModel();
    }

    @Inject
    private UserService userService;
    @Inject
    private CartService cartService;

    public void doLogin() {
        try {
            User user = userService.findByUsername(model.getUsername());
            if (user == null) {
                throw new SecurityException("Nincs ilyen felhasználó");
            }
            String hashedPassword = DigestUtils.md5Hex(model.getPassword());
            if (!hashedPassword.equals(user.getPassword())) {
                throw new SecurityException("Helytelen jelszó");
            }

            LoggedInUserModel loggedInUserModel = new LoggedInUserModel();
            loggedInUserModel.setUsername(user.getUserName());
            loggedInUserModel.setId(user.getId());
            loggedInUserModel.setRole(user.getRole());
            bean.setModel(loggedInUserModel);
            Long userId = loggedInUserModel.getId();
            cartService.getOrCreateCart(userId);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres Bejelentkezés", ""));
            PrimeFaces.current().executeScript("PF('loginDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sikertelen bejelentkezés", ""));
            e.printStackTrace();
        }
    }

    public void doLogout() {
        bean.deleteCart();
        bean.setModel(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres Kijelentkezés", ""));
    }

    public LoginModel getModel() {
        return model;
    }

    public void setModel(LoginModel model) {
        this.model = model;
    }
}
