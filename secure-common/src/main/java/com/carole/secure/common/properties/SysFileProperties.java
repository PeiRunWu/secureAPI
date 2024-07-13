package com.carole.secure.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/9 21:04
 * @Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "file")
public class SysFileProperties {

    /**
     * 上传类型
     */
    private String uploadType;
}
