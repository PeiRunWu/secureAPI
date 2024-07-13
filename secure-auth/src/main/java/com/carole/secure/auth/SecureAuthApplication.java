package com.carole.secure.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CaroLe
 * @Date 2023/10/16 21:30
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.carole")
@EnableFeignClients(basePackages = "com.carole.secure.api.feign")
public class SecureAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecureAuthApplication.class, args);
    }
}
