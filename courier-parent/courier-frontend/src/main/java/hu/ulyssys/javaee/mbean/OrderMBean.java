package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.Courier;
import hu.ulyssys.javaee.entity.Order;
import hu.ulyssys.javaee.entity.User;
import hu.ulyssys.javaee.service.CourierService;
import hu.ulyssys.javaee.service.OrderService;
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
public class OrderMBean implements Serializable {
    @Inject
    private OrderService orderService;
    @Inject
    private UserService userService;
    @Inject
    private CourierService courierService;

    List<User> userList;
    List<Courier> courierList;
    List<String> publicSpaceNature = new ArrayList<>();

    private Long modifierUserID;
    private Long creatorUserID;
    private Long courierID;

    List<Order> list = new ArrayList<>();
    private Order selectedOrder = new Order();

    private void load() {
        list = orderService.getAll();
        userList = userService.getAll();
        courierList = courierService.getAll();
    }

    @PostConstruct
    private void init() {
        publicSpaceNature.add("utca");
        publicSpaceNature.add("köz");
        publicSpaceNature.add("lugas");
        load();
    }

    public void remove() {
        orderService.remove(selectedOrder);
        initNewOrder();
        load();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful remove"));
    }

    public void initNewOrder() {
        this.selectedOrder = new Order();
    }

    public void save() {
        if (modifierUserID != null) {
            selectedOrder.setModifiedUser(userService.findById(modifierUserID));
        } else {
            selectedOrder.setModifiedUser(null);
        }
        if (courierID != null) {
            selectedOrder.setCourier(courierService.findById(courierID));
        } else {
            selectedOrder.setCourier(null);
        }

        if (selectedOrder.getId() == null) {
            selectedOrder.setCreatedDate(time());
            selectedOrder.setCreatorUser(userService.findById(creatorUserID));
            selectedOrder.setDeliveryDate(time().plusHours(1));
            orderService.add(selectedOrder);
        } else {
            selectedOrder.setCreatorUser(userService.findById(creatorUserID));
            selectedOrder.setModifiedDate(time());
            selectedOrder.setDeliveryDate(time().plusHours(1));
            orderService.update(selectedOrder);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save"));
        load();
        PrimeFaces.current().executeScript("PF('orderDialog').hide()");
    }
    private LocalDateTime time() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CourierService getCourierService() {
        return courierService;
    }

    public void setCourierService(CourierService courierService) {
        this.courierService = courierService;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Courier> getCourierList() {
        return courierList;
    }

    public void setCourierList(List<Courier> courierList) {
        this.courierList = courierList;
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

    public Long getCourierID() {
        return courierID;
    }

    public void setCourierID(Long courierID) {
        this.courierID = courierID;
    }

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public List<String> getPublicSpaceNature() {
        return publicSpaceNature;
    }

    public void setPublicSpaceNature(List<String> publicSpaceNature) {
        this.publicSpaceNature = publicSpaceNature;
    }
}
