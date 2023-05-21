package hu.ulyssys.javaee.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "app_order")
public class Order extends AbstractEntity {
    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
    @ManyToMany
    @JoinTable(name = "order_foods",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods;

    @Column(name = "settlement")
    private String settlement;

    @Column(name = "public_space")
    private String publicSpace;

    @Column(name = "public_space_nature")
    private String publicSpaceNature;

    @Column(name = "house_number")
    private String houseNumber;

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
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
