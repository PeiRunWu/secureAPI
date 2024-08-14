package com.carole.secure.toolbox.model.pojo;

import java.io.Serializable;

import com.carole.secure.common.annotation.TableField;
import com.carole.secure.common.enums.FieldTypeEnum;
import com.carole.secure.common.enums.OperationSupport;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2024/7/17 16:19
 * @Description
 */
@Data
public class DataSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(FieldTypeEnum.ASSIGN_ID)
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

    /**
     * 当前用户Id
     */
    private String loginId;
}
