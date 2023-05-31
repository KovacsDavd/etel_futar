package hu.ulyssys.javaee.rest.endpoint;


import hu.ulyssys.javaee.entity.Food;
import hu.ulyssys.javaee.rest.model.FoodModel;
import hu.ulyssys.javaee.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/food")
public class FoodRestService extends CoreRestService<Food, FoodModel> {
    @Inject
    private UserService userService;

    @Override
    protected Food dtoToEntity(Food food, FoodModel foodModel) {
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

    @Override
    protected Class<Food> getManagedClass() {
        return Food.class;
    }

    @Override
    protected FoodModel entityToDTO(Food food) {
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
