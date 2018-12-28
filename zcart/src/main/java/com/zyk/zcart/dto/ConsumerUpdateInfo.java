package com.zyk.zcart.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ConsumerUpdateInfo {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Size(min = 11, max = 20)
    private String mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
