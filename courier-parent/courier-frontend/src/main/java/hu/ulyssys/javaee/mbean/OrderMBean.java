package hu.ulyssys.javaee.mbean;

import hu.ulyssys.javaee.entity.*;
import hu.ulyssys.javaee.service.*;
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
    @Inject
    private FoodService foodService;
    @Inject
    private CartService cartService;

    @Inject
    private LoggedInUserMBean loggedInUserMBean;

    List<User> userList;
    List<Courier> courierList;
    private Long courierID;

    List<Order> list = new ArrayList<>();
    List<Order> userOrderList;
    private Order selectedOrder = new Order();
    private Order order = new Order();


    private void load() {
        list = orderService.getAll();
        userList = userService.getAll();
        courierList = courierService.getAll();
    }

    @PostConstruct
    private void init() {
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
        this.courierID = null;
    }

    public List<Order> getUserOrderList() {
        userOrderList = orderService.getListByUserId(loggedInUserMBean.getModel().getId());
        return userOrderList;
    }

    public void setUserOrderList(List<Order> userOrderList) {
        this.userOrderList = userOrderList;
    }

    public void save() {
        if (courierID != null) {
            selectedOrder.setCourier(courierService.findById(courierID));
        } else {
            selectedOrder.setCourier(null);
        }

        if (selectedOrder.getId() == null) {
            selectedOrder.setCreatedDate(time());
            selectedOrder.setCreatorUser(userService.findById(loggedInUserMBean.getModel().getId()));
            selectedOrder.setDeliveryDate(time().plusHours(1));
            orderService.add(selectedOrder);
        } else {
            selectedOrder.setModifiedDate(time());
            selectedOrder.setModifiedUser(userService.findById(loggedInUserMBean.getModel().getId()));
            selectedOrder.setDeliveryDate(time().plusHours(1));
            orderService.update(selectedOrder);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save"));
        load();
        PrimeFaces.current().executeScript("PF('orderDialog').hide()");
    }

    public String saveFromUser() {
        order.setCreatorUser(userService.findById(loggedInUserMBean.getModel().getId()));
        order.setModifiedUser(null);
        order.setCreatedDate(time());
        order.setModifiedDate(null);
        order.setCourier(null);
        order.setDeliveryDate(time().plusHours(1));

        List<OrderFood> orderFoods = new ArrayList<>();
        Cart cart = cartService.getCart(loggedInUserMBean.getModel().getId());
        List<CartItem> cartItems = cart.getItems();
        for (CartItem cartItem : cartItems) {
            OrderFood orderFood = new OrderFood();
            orderFood.setOrder(order);
            orderFood.setFood(cartItem.getFood());
            orderFoods.add(orderFood);
        }
        order.setFoods(orderFoods);
        orderService.add(order);
        cartService.clearCart(loggedInUserMBean.getModel().getId());
        return "history?faces-redirect=true";
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

        if (selectedOrder.getCourier() != null) {
            courierID = selectedOrder.getCreatorUser().getId();
        } else {
            courierID = null;
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
