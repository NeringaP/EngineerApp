package com.finalproject.engineerapp.model;

import com.finalproject.engineerapp.validation.PinCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {

    private Long id;
    @NotNull(message = "is required")
    @Size(min=2, message = "must have two or more characters")
    private String firstName = "";

    private String lastName;

    private String email;

    @PinCode(message = "must start with LT- and have 5 digits after")
    //@PinCode(value = "LT-", message = "must start with LT- and have 5 digits after", size = 8)
    private String pinCode;

    public Person() {
    }

    public Person(Long id, String firstName, String lastName, String email, String pinCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pinCode = pinCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
