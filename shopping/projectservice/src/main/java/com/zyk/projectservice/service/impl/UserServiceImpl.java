package com.zyk.projectservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyk.projectservice.dao.UserMapper;
import com.zyk.projectservice.dto.AddUser;
import com.zyk.projectservice.dto.UserListDTO;
import com.zyk.projectservice.dto.UserUpdateDTO;
import com.zyk.projectservice.po.User;
import com.zyk.projectservice.service.UserService;
import com.zyk.projectservice.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service("userService")
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
    public User selectByEmail(String email) {
        User user = userMapper.selectByEmail(email);
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = userMapper.getByUsername(username);
        return user;
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

    @Override
    public void update(UserUpdateDTO userUpdateDTO) {

        User user = userMapper.selectByEmail(userUpdateDTO.getEmail());
//        user.setUsername(userUpdateDTO.getUsername());
//        user.setName(userUpdateDTO.getName());
//        user.setAvatarUrl(userUpdateDTO.getAvatarUrl());
        user.setEncryptedPassword(DigestUtils.md5DigestAsHex(userUpdateDTO.getPassword().getBytes()));
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public PageInfo<UserListDTO> getUsersWithPage(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<UserListDTO> users = userMapper.selectWithPage();
        PageInfo<UserListDTO> userPageInfo = users.toPageInfo();
        return userPageInfo;
    }

    @Override
    public void batchDelete(Integer[] userIds) {
        userMapper.batchDelete(userIds);
    }

}
