package com.zyk.zcart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zyk.zcart.dao")
@SpringBootApplication
public class ZcartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZcartApplication.class, args);
    }

}

