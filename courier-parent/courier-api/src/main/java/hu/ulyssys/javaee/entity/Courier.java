package hu.ulyssys.javaee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

@Entity
public class Courier extends AbstractEntity {

    @Column(name = "first_name", nullable = false, length = 500)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 500)
    private String lastName;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\+36\\d{9}", message = "Invalid phone number format")
    private String phoneNumber;

    public boolean isFirstNameSameLastName(){ // igaz ha azonos
        if (lastName.equals(firstName))
            return true;
        return false;
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
