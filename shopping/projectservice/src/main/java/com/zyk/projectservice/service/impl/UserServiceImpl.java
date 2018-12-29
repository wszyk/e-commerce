package com.zyk.projectservice.service.impl;

import com.zyk.projectservice.dao.UserMapper;
import com.zyk.projectservice.dto.AddUser;
import com.zyk.projectservice.po.User;
import com.zyk.projectservice.service.UserService;
import com.zyk.projectservice.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@EnableAutoConfiguration
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public void add(AddUser addUser) {
        User user = new User();
        user.setUsername(addUser.getUsername());
        user.setName(addUser.getName());
        user.setEmail(addUser.getEmail());
        user.setAvatarUrl(addUser.getAvatarUrl());
        user.setEncryptedPassword(DigestUtils.md5DigestAsHex(addUser.getPassword().getBytes()));
        user.setRoles(Constant.rolesStr);
        userMapper.insert(user);
    }

}
