package hu.ulyssys.javaee.rest.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderModel extends CoreModel{
    private LocalDateTime deliveryDate;
    private Long courierID;
    private List<OrderFoodModel> foodList;
    private String settlement;
    private String publicSpace;
    private String publicSpaceNature;
    private String houseNumber;

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getCourierID() {
        return courierID;
    }

    public void setCourierID(Long courierID) {
        this.courierID = courierID;
    }

    public List<OrderFoodModel> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<OrderFoodModel> foodList) {
        this.foodList = foodList;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getPublicSpace() {
        return publicSpace;
    }

    public void setPublicSpace(String publicSpace) {
        this.publicSpace = publicSpace;
    }

    public String getPublicSpaceNature() {
        return publicSpaceNature;
    }

    public void setPublicSpaceNature(String publicSpaceNature) {
        this.publicSpaceNature = publicSpaceNature;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
