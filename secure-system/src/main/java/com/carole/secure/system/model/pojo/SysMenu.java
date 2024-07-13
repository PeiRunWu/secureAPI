package com.carole.secure.system.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.carole.secure.common.annotation.TableField;
import com.carole.secure.common.enums.FieldTypeEnum;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/11 21:24
 * @Description
 */
@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableField(FieldTypeEnum.ASSIGN_ID)
    private String id;

    /**
     * 父Id
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 状态(0-隐藏 1-显示)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
