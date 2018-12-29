package com.zyk.projectservice.dto;

import javax.validation.constraints.Size;

public class ConsumerChangePassword {
    private Integer consumerId;
    @Size(min = 6, max = 15)
    private String password;

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
