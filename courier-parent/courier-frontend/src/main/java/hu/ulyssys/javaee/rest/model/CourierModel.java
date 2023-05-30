package hu.ulyssys.javaee.rest.model;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class CourierModel extends CoreModel{
    private String firstName;
    private String lastName;
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
