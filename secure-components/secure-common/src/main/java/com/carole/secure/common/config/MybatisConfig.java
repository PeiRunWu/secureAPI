package com.carole.secure.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carole.secure.common.interceptor.TableIdInterceptor;

/**
 * @author CaroLe
 * @Date 2023/9/16 16:36
 * @Description
 */
@Configuration
public class MybatisConfig {
    @Bean
    public TableIdInterceptor tableIdInterceptor() {
        return new TableIdInterceptor();
    }
}
