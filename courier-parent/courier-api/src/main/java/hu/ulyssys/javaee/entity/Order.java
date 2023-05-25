package hu.ulyssys.javaee.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "app_order")
public class Order extends AbstractEntity {
    //TODO: Idő több mint a mostani
    @Column(name = "delivery_date", nullable = false)
    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderFood> foods;

    @Column(name = "settlement", nullable = false, length = 200)
    private String settlement;

    @Column(name = "public_space", nullable = false, length = 200)
    private String publicSpace;

    @Column(name = "public_space_nature", nullable = false, length = 200)
    private String publicSpaceNature;

    @Column(name = "house_number", nullable = false, length = 200)
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

    public List<OrderFood> getFoods() {
        return foods;
    }

    public void setFoods(List<OrderFood> foods) {
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
