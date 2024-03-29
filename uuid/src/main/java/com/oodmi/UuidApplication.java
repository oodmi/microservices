package com.oodmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UuidApplication {

    public static void main(String[] args) {
        SpringApplication.run(UuidApplication.class, args);
    }
}
