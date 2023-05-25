package hu.ulyssys.javaee.rest.model;

import hu.ulyssys.javaee.entity.Food;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

public class OrderModel {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long creatorID;
    private Long modifiedID;
    private LocalDateTime deliveryDate;
    private Long courierID;
    private List<Long> foodListID;
    private String settlement;
    private String publicSpace;
    private String publicSpaceNature;
    private String houseNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Long creatorID) {
        this.creatorID = creatorID;
    }

    public Long getModifiedID() {
        return modifiedID;
    }

    public void setModifiedID(Long modifiedID) {
        this.modifiedID = modifiedID;
    }

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

    public List<Long> getFoodListID() {
        return foodListID;
    }

    public void setFoodListID(List<Long> foodListID) {
        this.foodListID = foodListID;
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
