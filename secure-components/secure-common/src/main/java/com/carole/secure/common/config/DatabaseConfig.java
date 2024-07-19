package com.carole.secure.common.config;

import lombok.Builder;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2024/7/19 9:45
 * @Description
 */
@Data
@Builder
public class DatabaseConfig {
    
    /**
     * ip
     */
    private String ip;

    /**
     * 端口号
     */
    private String port;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 驱动类
     */
    private Integer driverType;
}
