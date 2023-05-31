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

    @Inject
    private LoggedInUserMBean loggedInUserMBean;

    List<Courier> list = new ArrayList<>();
    private Courier selectedCourier = new Courier();

    private void load() {
        list = courierService.getAll();
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
        if (selectedCourier.getId() == null) {
            selectedCourier.setCreatedDate(time());
            selectedCourier.setCreatorUser(userService.findById(loggedInUserMBean.getModel().getId()));
            if (selectedCourier.isFirstNameSameLastName(selectedCourier.getLastName(), selectedCourier.getFirstName())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Last illetve Firstname nem lehet azonos"));
            } else {
                courierService.add(selectedCourier);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save"));
                load();
                PrimeFaces.current().executeScript("PF('courierDialog').hide()");
            }
        } else {
            selectedCourier.setModifiedDate(time());
            selectedCourier.setModifiedUser(userService.findById(loggedInUserMBean.getModel().getId()));
            courierService.update(selectedCourier);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save"));
            load();
            PrimeFaces.current().executeScript("PF('courierDialog').hide()");
        }
    }

    private LocalDateTime time() {
        return LocalDateTime.now();
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
}
