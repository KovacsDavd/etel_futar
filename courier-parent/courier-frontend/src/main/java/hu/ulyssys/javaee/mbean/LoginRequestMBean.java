package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.mbean.model.LoggedInUserModel;
import hu.ulyssys.javaee.mbean.model.LoginModel;
import hu.ulyssys.javaee.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;

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
    private LoginModel model = new LoginModel();
    @Inject
    private UserService userService;

    public void doLogin() {
        try {
            User user = userService.findByUsername(model.getUsername());
            if (user==null){
                throw new SecurityException("Nincs ilyen felhasználó");
            }
            String hashedPassword = DigestUtils.md5Hex(model.getPassword());
            if (!hashedPassword.equals(user.getPassword())){
                throw new SecurityException("Helytelen jelszó");
            }

            LoggedInUserModel loggedInUserModel = new LoggedInUserModel();
            loggedInUserModel.setUsername(user.getUserName());
            loggedInUserModel.setId(user.getId());
            loggedInUserModel.setRole(user.getRole());
            bean.setModel(loggedInUserModel);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres Bejelentkezés", ""));

            PrimeFaces.current().executeScript("PF('loginDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sikertelen bejelentkezés", ""));
            e.printStackTrace();
        }
    }

    public void doLogout() {
        bean.setModel(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres Kijelentkezés", ""));
    }

    public LoggedInUserMBean getBean() {
        return bean;
    }

    public void setBean(LoggedInUserMBean bean) {
        this.bean = bean;
    }

    public LoginModel getModel() {
        return model;
    }

    public void setModel(LoginModel model) {
        this.model = model;
    }
}
