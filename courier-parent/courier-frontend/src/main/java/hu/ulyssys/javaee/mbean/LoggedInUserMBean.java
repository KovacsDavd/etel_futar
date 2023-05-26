package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.entity.UserRole;
import hu.ulyssys.javaee.mbean.model.LoggedInUserModel;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoggedInUserMBean implements Serializable {
    private LoggedInUserModel model;

    public boolean isLoggedIn() {
        return model !=null;
    }
    public boolean isAdmin() {
        return isLoggedIn() && model.getRole().equals(UserRole.ADMIN);
    }

    public LoggedInUserModel getModel() {
        return model;
    }

    public void setModel(LoggedInUserModel model) {
        this.model = model;
    }


}
