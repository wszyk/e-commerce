package com.zyk.projectadminapi.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zyk.projectadminapi.admin.dto.Authcode;
import com.zyk.projectadminapi.admin.dto.LoginInfo;
import com.zyk.projectadminapi.admin.exception.BackendClientException;
import com.zyk.projectadminapi.admin.mail.MailService;
import com.zyk.projectservice.dto.AddUser;
import com.zyk.projectservice.dto.UserListDTO;
import com.zyk.projectservice.dto.UserUpdateDTO;
import com.zyk.projectservice.po.User;
import com.zyk.projectservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RestController
@EnableAutoConfiguration
@RequestMapping("/Admin/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private com.zyk.projectadminapi.admin.mail.MailService MailService;
    @Autowired
    RedisTemplate redisTemplate;

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


    @GetMapping("/resetPassword")
    public void resetPassword(String email){

        int authcode=(int)((Math.random()*9+1)*100000);
        String str = String.valueOf(authcode);
        MailService.sendSimpleMail("1342944009@qq.com","test simple mail",str);
        //1. 随机生成随机码
        //2. 发送随机到email
        //3. email as key ,random code as value, store to redis (expire time)
      redisTemplate.opsForValue().set(email,str);
    }

    @GetMapping("/verifyCode")
    public void verifyEmailCode(HttpServletRequest request,@RequestParam String email, @RequestParam String code,@RequestParam String password) throws BackendClientException {

        String rediscode = (String) redisTemplate.opsForValue().get(email);
         if (rediscode==null){
             throw new BackendClientException("rediscode is null");
         }
         if(!code.equals(rediscode)){
             throw new BackendClientException("email verify code is invalid");
         }
//        changePassword(email,password);
        //1. get code by email from redis
        //   code null 过期 抛异常
        //2. compare input code with redis code
        //3. 相同验证通过，密码重置123456，
        //   否则验证不同 throw exception
        System.out.println(email+password);
        String authorizationStr = request.getHeader("Authorization");
        System.out.println(authorizationStr);
        //自定修改，返回Token，授权一个修改密码的api


    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestAttribute String email, @RequestParam String password){
        UserUpdateDTO update=new UserUpdateDTO();
        update.setEmail(email);
        update.setPassword(password);
        userService.update(update);
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

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody Integer[] userIds){
        userService.batchDelete(userIds);
    }
}
