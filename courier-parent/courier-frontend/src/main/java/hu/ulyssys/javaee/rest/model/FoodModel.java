package hu.ulyssys.javaee.rest.model;

import java.time.LocalDateTime;

public class FoodModel extends CoreModel{
    private String name;
    private String description;
    private Integer price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
