package com.carole.secure.toolbox.model.dto;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2024/7/18 10:02
 * @Description
 */
@Data
public class DataSourceDTO {

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
     * 驱动类
     */
    private Integer driverType;
}
