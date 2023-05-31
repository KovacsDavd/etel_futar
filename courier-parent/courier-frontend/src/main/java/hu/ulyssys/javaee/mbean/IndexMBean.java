package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.service.CartService;
import hu.ulyssys.javaee.service.FoodService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ViewScoped
public class IndexMBean implements Serializable {
    @Inject
    private LoggedInUserMBean bean;
    @Inject
    private FoodService foodService;
    @Inject
    private CartService cartService;

    List<Food> list = new ArrayList<>();

    private void load() {
        list = foodService.getAll();
    }

    @PostConstruct
    private void init() {
        load();
    }

    public void add(Food food) {
        cartService.addToCart(bean.getModel().getId(), food);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres kosárhoz adás", ""));
    }

    public List<Food> getList() {
        return list;
    }

    public void setList(List<Food> list) {
        this.list = list;
    }

    public FoodService getService() {
        return foodService;
    }

    public void setService(FoodService service) {
        this.foodService = service;
    }

}
