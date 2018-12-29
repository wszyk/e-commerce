package com.zyk.zcart.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ConsumerUpdateInfo {
    private String name;

    @Email
    private String email;
    @Size(min = 11, max = 20)
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
