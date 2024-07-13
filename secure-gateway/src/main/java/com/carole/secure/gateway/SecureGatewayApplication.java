package com.carole.secure.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author CaroLe
 * @Date 2023/9/20 20:31
 * @Description
 */
@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.carole")
@EnableFeignClients(basePackages = "com.carole.secure.api.feign")
public class SecureGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecureGatewayApplication.class, args);
    }
}
