package com.carole.secure.system.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.carole.secure.common.annotation.TableField;
import com.carole.secure.common.enums.FieldTypeEnum;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/10 21:22
 * @Description
 */
@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableField(FieldTypeEnum.ASSIGN_ID)
    private String id;

    /**
     * 角色昵称
     */
    private String roleName;

    /**
     * 状态(0-隐藏 1-显示)
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
