package hu.ulyssys.javaee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "courier")
public class Courier extends AbstractEntity {
    //TODO: nem lehet azonos a név
    @Column(name = "first_name", nullable = false, length = 500)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 500)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    @Pattern(regexp = "\\+36\\d{9}", message = "Invalid phone number format")
    private String phoneNumber;

    public boolean isFirstNameSameLastName(String lastName, String firstName){ // igaz ha azonos
        return lastName.equals(firstName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
