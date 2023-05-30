package hu.ulyssys.javaee.rest.endpoint;

import hu.ulyssys.javaee.entity.Order;
import hu.ulyssys.javaee.entity.OrderFood;
import hu.ulyssys.javaee.rest.model.OrderFoodModel;
import hu.ulyssys.javaee.rest.model.OrderModel;
import hu.ulyssys.javaee.rest.request.RestFunctionRequest;
import hu.ulyssys.javaee.rest.response.RestFindAllResponse;
import hu.ulyssys.javaee.rest.response.RestFindByIdResponse;
import hu.ulyssys.javaee.service.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/order")
public class OrderRestService extends CoreRestService<Order, OrderModel> {
    @Inject
    private OrderService orderService;

    @Inject
    private UserService userService;
    @Inject
    private CourierService courierService;

    @Inject
    private FoodService foodService;

    @Override
    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(RestFunctionRequest<OrderModel> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Order order = dtoToEntity(new Order(), request.getModel(), true);
        orderService.add(order);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(order))).build();

    }

    @Override
    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(RestFunctionRequest<OrderModel> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Order order = orderService.findById(request.getModel().getId());
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dtoToEntity(order, request.getModel(), false);
        orderService.update(order);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(order))).build();
    }


    private Order dtoToEntity(Order order, OrderModel orderModel, boolean createOrderFoods) {
        order.setCreatorUser(userService.findById(orderModel.getCreatorID()));
        if (orderModel.getModifiedDate() != null) {
            order.setModifiedDate(orderModel.getModifiedDate());
        }
        if (orderModel.getModifiedID() != null) {
            order.setModifiedUser(userService.findById(orderModel.getModifiedID()));
        }
        if (orderModel.getCourierID() != null) {
            order.setCourier(courierService.findById(orderModel.getCourierID()));
        }
        order.setSettlement(orderModel.getSettlement());
        order.setCreatedDate(orderModel.getCreatedDate());
        order.setDeliveryDate(orderModel.getDeliveryDate());
        order.setHouseNumber(orderModel.getHouseNumber());
        order.setPublicSpace(orderModel.getPublicSpace());
        order.setPublicSpaceNature(orderModel.getPublicSpaceNature());
        if (orderModel.getFoodList() != null) {
            if (createOrderFoods) { //POST
                List<OrderFood> orderFoods = new ArrayList<>();
                for (OrderFoodModel orderFoodModel : orderModel.getFoodList()) {
                    OrderFood orderFood = new OrderFood();
                    orderFood.setFood(foodService.findById(orderFoodModel.getFoodId()));
                    orderFood.setOrder(order);
                    orderFoods.add(orderFood);
                }
                order.setFoods(orderFoods);
            } else { //PUT
                // NEM FRISSÍTJÜK AZ ORDERFOOD LISTÁT
            }
        } else {
            order.setFoods(null);
        }
        return order;
    }

    @Override
    protected OrderModel entityToDTO(Order order) {
        OrderModel model = new OrderModel();
        model.setId(order.getId());
        model.setCreatedDate(order.getCreatedDate());
        model.setDeliveryDate(order.getDeliveryDate());
        if (order.getModifiedUser() != null) {
            model.setModifiedID(order.getModifiedUser().getId());
        }
        if (order.getModifiedDate() != null) {
            model.setModifiedDate(order.getModifiedDate());
        }
        if (order.getCourier() != null) {
            model.setCourierID(order.getCourier().getId());
        }
        model.setCreatorID(order.getCreatorUser().getId());
        model.setHouseNumber(order.getHouseNumber());
        model.setPublicSpace(order.getPublicSpace());
        model.setPublicSpaceNature(order.getPublicSpaceNature());
        model.setSettlement(order.getSettlement());
        if (order.getFoods() != null) {
            List<OrderFoodModel> foodList = new ArrayList<>();
            for (OrderFood orderFood : order.getFoods()) {
                OrderFoodModel orderFoodModel = new OrderFoodModel();
                orderFoodModel.setOrderId(orderFood.getOrder().getId());
                orderFoodModel.setFoodId(orderFood.getFood().getId());
                foodList.add(orderFoodModel);
            }
            model.setFoodList(foodList);
        }
        return model;
    }

    @Override
    protected Order dtoToEntity(Order entity, OrderModel model) {
        return null;
    }

    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }
}
