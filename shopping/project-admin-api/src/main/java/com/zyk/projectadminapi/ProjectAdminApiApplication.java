package com.zyk.projectadminapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages="com.zyk")
@EnableCaching
public class ProjectAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAdminApiApplication.class, args);
    }

}

