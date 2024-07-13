package com.carole.secure.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CaroLe
 * @Date 2023/9/20 21:13
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SecureFrameworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecureFrameworkApplication.class, args);
    }
}
