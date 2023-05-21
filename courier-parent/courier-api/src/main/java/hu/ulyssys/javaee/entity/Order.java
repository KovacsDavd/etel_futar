package hu.ulyssys.javaee.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order extends AbstractEntity {
    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
    //TODO: ManyToMany kapcsolat megoldása
    /*@ManyToMany
    @JoinTable(name = "foods", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods;*/

    @Column(name = "settlement")
    private String settlement;

    @Column(name = "public_space")
    private String publicSpace;

    @Column(name = "public_space_nature")
    private String publicSpaceNature;

    @Column(name = "house_number")
    private String houseNumber;
}
