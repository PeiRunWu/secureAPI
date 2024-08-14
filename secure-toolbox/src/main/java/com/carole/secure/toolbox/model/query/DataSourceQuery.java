package com.carole.secure.toolbox.model.query;

import lombok.Builder;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2024/7/18 10:04
 * @Description
 */
@Data
@Builder
public class DataSourceQuery {
    /**
     * 当前页
     */
    private Integer current;

    /**
     * 当前页大小
     */
    private Integer pageSize;

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 库名
     */
    private String tableName;

    /**
     * 驱动类型
     */
    private String driverType;

    /**
     * 登入用户Id
     */
    private String loginId;
}
