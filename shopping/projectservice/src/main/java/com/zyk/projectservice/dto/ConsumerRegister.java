package com.zyk.projectservice.dto;

public class ConsumerRegister {
    private String name;
    private String email;
    private String mobile;
    private String password;
    private Boolean Newsubscribe;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getNewsubscribe() {
        return Newsubscribe;
    }

    public void setNewsubscribe(Boolean newsubscribe) {
        Newsubscribe = newsubscribe;
    }
}
