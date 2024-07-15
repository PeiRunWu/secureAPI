package com.carole.secure.elasticsearch.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/11 20:26
 * @Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "elastic")
public class ElasticProperties {

    /**
     * 地址
     */
    private String hostname;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 协议
     */
    private String scheme;
}
