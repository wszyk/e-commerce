package com.zyk.zcart.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyk.zcart.dao.UserMapper;
import com.zyk.zcart.dto.AddUser;
import com.zyk.zcart.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
@RequestMapping("/Admin/User")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/add")
    public Long add(@RequestBody AddUser addUser) {
        //todo check params, email and passowrd lenght
        User user = new User();
        user.setUsername(addUser.getUsername());
        user.setName(addUser.getName());
        user.setEmail(addUser.getEmail());
        user.setAvatarUrl(addUser.getAvatarUrl());
        String originPassword = addUser.getPassword();
        String encryptedPassword = DigestUtils.md5DigestAsHex(originPassword.getBytes());
        user.setEncryptedPassword(encryptedPassword);
        String[] roles = {"user"};
        user.setRoles(JSON.toJSONString(roles));
        userMapper.insert(user);
        return user.getUserId();
    }

    @GetMapping("/login")
    public String login(String username, String password) throws Exception {
        User user = userMapper.selectByUsername(username);
        if (user == null){
            throw new Exception("user doesn't exist");
        }
        String inputEncryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        String encryptedPassword = user.getEncryptedPassword();
        if (!inputEncryptedPassword.equals(encryptedPassword)){
            throw new Exception("password doesn't match");
        }
        JSONObject loginJson = new JSONObject();
        loginJson.put("userid",user.getUserId());
        loginJson.put("username",username);
        loginJson.put("roles",user.getRoles());
        String loginJsonStr = loginJson.toJSONString();
        //todo add encrypted token
        return "登录";
    }
}
