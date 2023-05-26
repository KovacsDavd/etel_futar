package hu.ulyssys.javaee.rest.model;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class CourierModel {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long creatorID;
    private Long modifiedID;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "\\+36\\d{9}", message = "Invalid phone number format")
    private String phoneNumber;
    public boolean isFirstNameSameLastName(String lastName, String firstName){ // igaz ha azonos
        return lastName.equals(firstName);
    }

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
