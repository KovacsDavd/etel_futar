package hu.ulyssys.javaee.rest.endpoint;


import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.rest.model.FoodModel;
import hu.ulyssys.javaee.rest.request.RestFunctionRequest;
import hu.ulyssys.javaee.rest.response.RestFindAllResponse;
import hu.ulyssys.javaee.rest.response.RestFindByIdResponse;
import hu.ulyssys.javaee.service.FoodService;
import hu.ulyssys.javaee.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/food")
public class FoodRestService {

    @Inject
    private FoodService foodService;
    @Inject
    private UserService userService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        Food food = foodService.findById(id);
        if (food == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new RestFindByIdResponse<>(entityToDTO(food))).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        List<FoodModel> list = new ArrayList<>();
        foodService.getAll().forEach(food -> {
            list.add(entityToDTO(food));
        });

        return Response.ok(new RestFindAllResponse<>(list)).build();
    }

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(RestFunctionRequest<FoodModel> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Food food = dtoToEntity(new Food(), request.getModel());
        foodService.add(food);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(food))).build();

    }

    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(RestFunctionRequest<FoodModel> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Food food = foodService.findById(request.getModel().getId());
        if (food == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dtoToEntity(food, request.getModel());
        foodService.update(food);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(food))).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Long id) {
        Food food = foodService.findById(id);
        if (food == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        foodService.remove(food);
        return Response.ok().build();
    }

    private Food dtoToEntity(Food food, FoodModel foodModel) {
        food.setCreatorUser(userService.findById(foodModel.getCreatorID()));
        if (foodModel.getModifiedDate() != null) {
            food.setModifiedDate(foodModel.getModifiedDate());
        }
        if (foodModel.getModifiedID() != null) {
            food.setModifiedUser(userService.findById(foodModel.getModifiedID()));
        }
        food.setName(foodModel.getName());
        food.setPrice(foodModel.getPrice());
        food.setDescription(foodModel.getDescription());
        food.setCreatedDate(foodModel.getCreatedDate());
        return food;
    }

    private FoodModel entityToDTO(Food food) {
        FoodModel model = new FoodModel();
        model.setId(food.getId());
        model.setCreatedDate(food.getCreatedDate());
        model.setDescription(food.getDescription());
        if (food.getModifiedUser() != null) {
            model.setModifiedID(food.getModifiedUser().getId());
        }
        if (food.getModifiedDate() != null) {
            model.setModifiedDate(food.getModifiedDate());
        }
        model.setCreatorID(food.getCreatorUser().getId());
        model.setName(food.getName());
        model.setPrice(food.getPrice());
        return model;
    }
}
