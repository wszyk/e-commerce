package com.zyk.projectadminapi.admin.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zyk.projectadminapi.admin.dto.LoginInfo;
import com.zyk.projectadminapi.admin.exception.BackendClientException;
import com.zyk.projectservice.dto.AddUser;
import com.zyk.projectservice.dto.UserListDTO;
import com.zyk.projectservice.dto.UserUpdateDTO;
import com.zyk.projectservice.po.User;
import com.zyk.projectservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;

@RestController
@EnableAutoConfiguration
@RequestMapping("/Admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getById")
    public User getById(@RequestParam Long userId){
        User user = userService.getById(userId);
        return user;
    }
    @GetMapping("/getCurrentUserInfo")
    public User getCurrentUserInfo(@RequestAttribute Long userId){
        User currentUser = userService.getById(userId);
        return currentUser;
    }
    @PostMapping("/add")
    public void add(@RequestBody AddUser addUser){
        userService.add(addUser);
    }

    @GetMapping("/login")
    public String login(String username, String password) throws BackendClientException {
        User user = userService.getByUsername(username);
        if (user == null){
            throw new BackendClientException("user doesn't exist");
        }
        String inputEncryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!user.getEncryptedPassword().equals(inputEncryptedPassword)){
            throw new BackendClientException("password is invalid");
        }
        LoginInfo loginInfo = new LoginInfo(user.getUserId(), user.getUsername(), user.getRoles(), new Date());
        String loginInfoStr = JSON.toJSONString(loginInfo);
        //todo encrypt token
        String token = Base64.getEncoder().encodeToString(loginInfoStr.getBytes());
        return token;
    }
    @PostMapping("/update")
    public void update(@RequestBody UserUpdateDTO userUpdateDTO){
        userService.update(userUpdateDTO);
    }

    @GetMapping("/getUsersWithPage")
    public PageInfo<UserListDTO> getUsersWithPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum){
        PageInfo<UserListDTO> usersWithPage = userService.getUsersWithPage(pageNum);
        return usersWithPage;
    }


}
