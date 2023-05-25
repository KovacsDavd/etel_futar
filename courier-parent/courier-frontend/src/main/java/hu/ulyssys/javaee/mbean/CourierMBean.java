package hu.ulyssys.javaee.mbean;


import hu.ulyssys.javaee.entity.Courier;
import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.service.CourierService;
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
public class CourierMBean implements Serializable {
    @Inject
    private CourierService courierService;
    @Inject
    private UserService userService;

    List<User> userList;
    List<Courier> list = new ArrayList<>();
    private Courier selectedCourier = new Courier();
    private Long modifierUserID;
    private Long creatorUserID;

    private void load() {
        list = courierService.getAll();
        userList = userService.getAll();
    }

    @PostConstruct
    private void init() {
        load();
    }

    public void remove() {
        courierService.remove(selectedCourier);
        initNewCourier();
        load();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful remove"));
    }

    public void initNewCourier() {
        this.selectedCourier = new Courier();
    }

    public void save() {
        if (modifierUserID != null) {
            selectedCourier.setModifiedUser(userService.findById(modifierUserID));
        } else {
            selectedCourier.setModifiedUser(null);
        }

        if (selectedCourier.getId() == null) {
            selectedCourier.setCreatedDate(time());
            selectedCourier.setCreatorUser(userService.findById(creatorUserID));
            courierService.add(selectedCourier);
        } else {
            selectedCourier.setModifiedDate(time());
            courierService.update(selectedCourier);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save"));
        load();
        PrimeFaces.current().executeScript("PF('courierDialog').hide()");
    }

    private LocalDateTime time() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }


    public CourierService getCourierService() {
        return courierService;
    }

    public void setCourierService(CourierService courierService) {
        this.courierService = courierService;
    }

    public List<Courier> getList() {
        return list;
    }

    public void setList(List<Courier> list) {
        this.list = list;
    }

    public Courier getSelectedCourier() {
        return selectedCourier;
    }

    public void setSelectedCourier(Courier selectedCourier) {
        this.selectedCourier = selectedCourier;
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