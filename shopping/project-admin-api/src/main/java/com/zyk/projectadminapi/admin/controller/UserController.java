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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.util.*;

@CrossOrigin
@RestController
@EnableAsync
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
    public void resetPassword(String email) throws BackendClientException {
        int authcode=(int)((Math.random()*9+1)*100000);
        String str = String.valueOf(authcode);
        User user = userService.selectByEmail(email);
        if(user==null){
            throw new BackendClientException("The E-Mail Address was not found in our records");
        }else {
            MailService.sendSimpleMail(email,"test simple mail",str);
            redisTemplate.opsForValue().set(email,str);
        }
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
        changePassword(email,password);
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
    public void batchDelete(@RequestBody Integer[] userIds, @RequestAttribute Integer currentUserId) throws BackendClientException {

        List<Integer> list = Arrays.asList(userIds);
        boolean contains = list.contains(currentUserId);
        if (contains){
            throw new BackendClientException("cannot delete current user");
        }
        userService.batchDelete(userIds);
    }
    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile file) throws Exception {
        //List<MultipartFile> unsupportTypes = files.stream().filter(f -> !f.getContentType().equals("image/png") && !f.getContentType().equals("image/jpeg")).collect(Collectors.toList());
        String contentType = file.getContentType();
        if (!contentType.equals("image/png") && !contentType.equals("image/jpeg")){
            throw new BackendClientException("file only support png or jpg");
        }

        String uuid = UUID.randomUUID().toString();
        String type = file.getContentType();
        type = type.split("/")[1];
        String fileName = String.format("%s.%s",uuid,type);
        String url = String.format("D:/Baw/实训/电商/project/shopping-front/avatarimg/%s", fileName);
        storeAvatar(file.getBytes(),url);
        return url;
    }
//    @PostMapping("/uploadAvatars")
//    public void uploadAvatars(@RequestParam("files") List<MultipartFile> files) throws Exception {
//        //不符合条件的类型
//        List<MultipartFile> unsupportTypes = files.stream().filter(f -> !f.getContentType().equals("image/png") && !f.getContentType().equals("image/jpeg")).collect(Collectors.toList());
//        if (unsupportTypes.size() > 0){
//            throw new BackendClientException("file only support png or jpg");
//        }
//
//        for (MultipartFile file :
//                files) {
//            String uuid = UUID.randomUUID().toString();
//            String type = file.getContentType();
//            type = type.split("/")[1];
//            String url = String.format("avatarimg/%s.%s", uuid, type);
//            storeAvatar(file.getBytes(),url);
//        }
//    }
    private void storeAvatar(byte[] imgData, String fileName) throws Exception {
        System.out.println(fileName);
        FileOutputStream out = new FileOutputStream(fileName);
        out.write(imgData);
        out.close();
    }
}
