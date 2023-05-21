package hu.ulyssys.javaee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Food extends AbstractEntity {
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
