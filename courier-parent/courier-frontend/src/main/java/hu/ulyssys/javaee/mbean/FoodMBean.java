package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.service.FoodService;
import hu.ulyssys.javaee.service.UserService;
import org.primefaces.PrimeFaces;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.*;

@Named
@ViewScoped
public class FoodMBean implements Serializable {
    @Inject
    private FoodService service;
    @Inject
    private UserService userService;

    List<Food> list = new ArrayList<>();
    List<User> userList;
    private Food selectedFood = new Food();
    private Long modifierUserID;
    private Long creatorUserID;

    private void load() {
        list = service.getAll();
        userList = userService.getAll();
    }

    @PostConstruct
    private void init() {
        load();
    }

    public void remove() {
        service.remove(selectedFood);
        initNewFood();
        load();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful remove"));
    }

    public void initNewFood() {
        this.selectedFood = new Food();
        this.creatorUserID = null;
        this.modifierUserID = null;
    }

    public void save() {
        if (modifierUserID != null) {
            selectedFood.setModifiedUser(userService.findById(modifierUserID));
        } else {
            selectedFood.setModifiedUser(null);
        }

        if (selectedFood.getId() == null) {
            selectedFood.setCreatedDate(time());
            selectedFood.setCreatorUser(userService.findById(creatorUserID));
            service.add(selectedFood);
        } else {
            selectedFood.setCreatorUser(userService.findById(creatorUserID));
            selectedFood.setModifiedDate(time());
            service.update(selectedFood);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save"));
        load();
        PrimeFaces.current().executeScript("PF('foodDialog').hide()");
    }

    private LocalDateTime time() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    public List<Food> getList() {
        return list;
    }

    public void setList(List<Food> list) {
        this.list = list;
    }

    public FoodService getService() {
        return service;
    }

    public void setService(FoodService service) {
        this.service = service;
    }

    public Food getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(Food selectedFood) {
        this.selectedFood = selectedFood;

        if (selectedFood != null) {
            if (selectedFood.getCreatorUser() != null) {
                creatorUserID = selectedFood.getCreatorUser().getId();
            } else {
                creatorUserID = null;
            }

            if (selectedFood.getModifiedUser() != null) {
                modifierUserID = selectedFood.getModifiedUser().getId();
            } else {
                modifierUserID = null;
            }
        } else {
            creatorUserID = null;
            modifierUserID = null;
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Long getModifierUserID() {
        return modifierUserID;
    }

    public void setModifierUserID(Long modifierUserID) {
        this.modifierUserID = modifierUserID;
    }

    public Long getCreatorUserID() {
        return creatorUserID;
    }

    public void setCreatorUserID(Long creatorUserID) {
        this.creatorUserID = creatorUserID;
    }
}
