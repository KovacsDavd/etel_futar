package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.service.FoodService;
import hu.ulyssys.javaee.service.UserService;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
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
public class FoodMBean implements Serializable {
    @Inject
    private FoodService service;
    @Inject
    private UserService userService;
    @Inject
    private LoggedInUserMBean loggedInUserMBean;

    List<Food> list = new ArrayList<>();
    List<User> userList;
    private Food selectedFood = new Food();

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
    }

    public void save() {
        if (selectedFood.getId() == null) {
            selectedFood.setCreatedDate(time());
            selectedFood.setCreatorUser(userService.findById(loggedInUserMBean.getModel().getId()));
            service.add(selectedFood);
        } else {
            selectedFood.setModifiedUser(userService.findById(loggedInUserMBean.getModel().getId()));
            selectedFood.setModifiedDate(time());
            service.update(selectedFood);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save"));
        load();
        PrimeFaces.current().executeScript("PF('foodDialog').hide()");
    }

    private LocalDateTime time() {
        return LocalDateTime.now();
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
    }
}
