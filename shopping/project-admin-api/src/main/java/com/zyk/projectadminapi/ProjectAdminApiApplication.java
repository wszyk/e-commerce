package com.zyk.projectadminapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages="com.zyk")
@EnableCaching
@EnableAsync
public class ProjectAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAdminApiApplication.class, args);
    }

}

