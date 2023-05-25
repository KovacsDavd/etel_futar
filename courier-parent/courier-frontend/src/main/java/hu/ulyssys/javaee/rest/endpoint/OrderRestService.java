package hu.ulyssys.javaee.rest.endpoint;

import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.entity.Order;
import hu.ulyssys.javaee.rest.model.OrderModel;
import hu.ulyssys.javaee.rest.request.RestFunctionRequest;
import hu.ulyssys.javaee.rest.response.RestFindAllResponse;
import hu.ulyssys.javaee.rest.response.RestFindByIdResponse;
import hu.ulyssys.javaee.service.CourierService;
import hu.ulyssys.javaee.service.FoodService;
import hu.ulyssys.javaee.service.OrderService;
import hu.ulyssys.javaee.service.UserService;
import org.apache.xpath.operations.Or;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/order")
public class OrderRestService {
    @Inject
    private OrderService orderService;
    @Inject
    private UserService userService;
    @Inject
    private CourierService courierService;

    @Inject
    private FoodService foodService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {

        Order order = orderService.findById(id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new RestFindByIdResponse<>(entityToDTO(order))).build();
    }


    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        List<OrderModel> list = new ArrayList<>();
        orderService.getAll().forEach(order -> {
            list.add(entityToDTO(order));
        });

        return Response.ok(new RestFindAllResponse<>(list)).build();
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(RestFunctionRequest<OrderModel> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Order order = dtoToEntity(new Order(), request.getModel());
        orderService.add(order);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(order))).build();

    }

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
        dtoToEntity(order, request.getModel());
        orderService.update(order);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(order))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        orderService.remove(order);
        return Response.ok().build();
    }

    private Order dtoToEntity(Order order, OrderModel orderModel) {
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
        /*if (orderModel.getFoodListID() != null) {
            if (orderModel.getFoodListID() != null) {
                List<Food> foods = new ArrayList<>();
                for (Long foodId : orderModel.getFoodListID()) {
                    Food food = foodService.findById(foodId);
                    if (food != null) {
                        foods.add(food);
                    }
                }
                order.setFoods(foods);
            } else {
                order.setFoods(null);
            }
        }*/
        return order;
    }

    private OrderModel entityToDTO(Order order) {
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
        /*if (order.getFoods() != null) {
            List<Long> foodsID = new ArrayList<>();
            for (Food food : order.getFoods()) {
                foodsID.add(food.getId());
            }
            model.setFoodListID(foodsID);
        }*/
        return model;
    }
}
