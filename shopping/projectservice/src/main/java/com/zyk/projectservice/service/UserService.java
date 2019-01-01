package com.zyk.projectservice.service;

import com.github.pagehelper.PageInfo;
import com.zyk.projectservice.dto.AddUser;
import com.zyk.projectservice.dto.UserListDTO;
import com.zyk.projectservice.dto.UserUpdateDTO;
import com.zyk.projectservice.po.User;

public interface UserService {

    User getById(Long userId);

    User getByUsername(String username);

    void add(AddUser user);

    void update(UserUpdateDTO userUpdateDTO);

    PageInfo<UserListDTO> getUsersWithPage(Integer pageNum);

}
