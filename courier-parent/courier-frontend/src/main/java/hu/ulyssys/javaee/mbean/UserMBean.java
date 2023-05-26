package hu.ulyssys.javaee.mbean;


import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UserMBean implements Serializable {
    @Inject
    private UserService userService;

    List<User> list = new ArrayList<>();
    private User selectedUser = new User();

    private void load() {
        list = userService.getAll();
    }

    @PostConstruct
    private void init() {
        load();
    }

    public void remove() {
        userService.remove(selectedUser);
        initNewUser();
        load();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful remove"));
    }

    public void initNewUser() {
        this.selectedUser = new User();
    }

    public void save() {
        if (selectedUser.getId() == null) {
            selectedUser.setCreatedDate(time());
            selectedUser.setPassword(hashPassword(selectedUser.getPassword()));
            userService.add(selectedUser);
        } else {
            userService.update(selectedUser);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save"));
        load();
        PrimeFaces.current().executeScript("PF('userDialog').hide()");
    }
    private LocalDateTime time() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    private String hashPassword(String passwd) {
        return DigestUtils.md5Hex(passwd);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}
