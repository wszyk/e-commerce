package com.zyk.zcart.controller;

import com.zyk.zcart.dto.ConsumerRegister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @PostMapping("/register")
    public int register(@RequestBody ConsumerRegister consumerRegister){
        return 1;
    }

}
