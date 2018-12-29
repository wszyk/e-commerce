package com.zyk.projectadminapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages="com.zyk")
public class ProjectAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAdminApiApplication.class, args);
    }

}

