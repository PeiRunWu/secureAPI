package com.carole.secure.system.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.carole.secure.common.annotation.TableField;
import com.carole.secure.common.enums.FieldTypeEnum;
import com.carole.secure.common.enums.OperationSupport;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/9/13 22:43
 * @Description 用户表
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableField(FieldTypeEnum.ASSIGN_ID)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String nickname;

    /**
     * 密码
     */
    @TableField(value = FieldTypeEnum.BCRYPT, operations = OperationSupport.BOTH)
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
