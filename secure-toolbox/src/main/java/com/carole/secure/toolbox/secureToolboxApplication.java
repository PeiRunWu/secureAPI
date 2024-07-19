package com.carole.secure.toolbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CaroLe
 * @Date 2024/7/16 15:47
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.carole")
@EnableFeignClients(basePackages = "com.carole.secure.api.feign")
public class secureToolboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(secureToolboxApplication.class, args);
    }
}
