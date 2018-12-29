package com.zyk.projectservice.service;

import com.zyk.projectservice.dto.AddUser;
import com.zyk.projectservice.po.User;

public interface UserService {

    User getById(Long userId);

    User getByUsername(String username);

    void add(AddUser user);

}
