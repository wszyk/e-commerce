package com.zyk.projectservice.dto;

public class UserUpdateDTO extends AddUser {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
