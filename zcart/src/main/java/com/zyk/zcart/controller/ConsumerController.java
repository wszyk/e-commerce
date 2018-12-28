package com.zyk.zcart.controller;

import com.zyk.zcart.dto.ConsumerChangePassword;
import com.zyk.zcart.dto.ConsumerRegister;
import com.zyk.zcart.dto.ConsumerUpdateInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @PostMapping("/register")
    public int register(@RequestBody ConsumerRegister consumerRegister){
        return 1;
    }
    @GetMapping("/login")
    public void login(@Email String email, @Size(min = 6, max = 15) String password){

    }

    @PostMapping("/updateInfo")
    public void updateInfo(@RequestBody @Validated ConsumerUpdateInfo consumerUpdateInfo){

    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody @Validated ConsumerChangePassword consumerChangePassword){

    }

    @GetMapping("/sendEmailVerifyCode")
    public String sendEmailVerifyCode(@Email String email){
        return "ABABAB";
    }

    @GetMapping("/sendMobileVerifyCode")
    public String sendMobileVerifyCode(@Size(min = 11,max = 20) String mobile){
        return "ABABAB";
    }
    @GetMapping("/checkEmailVerifyCode")
    public void checkEmailVerifyCode(@Email String email, @Size(min = 6, max = 6) String verifyCode){

    }

    @GetMapping("/checkMobileVerifyCode")
    public void checkMobileVerifyCode(@Size(min = 11, max = 15) String mobile, @Size(min = 6, max = 6) String verifyCode){

    }

}
