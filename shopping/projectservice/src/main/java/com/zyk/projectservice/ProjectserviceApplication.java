package com.zyk.projectservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zyk.projectservice.dao")
public class ProjectserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectserviceApplication.class, args);
    }

}

