package com.carole.secure.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CaroLe
 * @Date 2023/9/13 22:21
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.carole")
@EnableFeignClients(basePackages = "com.carole.secure.api.feign")
public class SecureSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecureSystemApplication.class, args);
    }
}
