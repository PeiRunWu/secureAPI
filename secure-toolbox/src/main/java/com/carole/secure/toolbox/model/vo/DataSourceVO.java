package com.carole.secure.toolbox.model.vo;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2024/7/16 15:54
 * @Description
 */
@Data
public class DataSourceVO {

    /**
     * id
     */
    private String id;

    /**
     * 数据源名称
     */
    private String name;

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
