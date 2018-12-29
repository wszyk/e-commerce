package com.zyk.projectadminapi.admin;

import com.zyk.projectadminapi.exception.BackendClientException;
import com.zyk.projectservice.dto.AddUser;
import com.zyk.projectservice.po.User;
import com.zyk.projectservice.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
@RestController
@EnableAutoConfiguration
@RequestMapping("/Admin/User")
public class UserController {

    @Autowired(required=true)
    private UserServiceImpl userService;

    @GetMapping("/getById")
    public User getById(@RequestParam Long userId){
        User user = userService.getById(userId);
        return user;
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
        return "token";
    }

}
