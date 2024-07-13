package com.carole.secure.system.model.vo;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/13 22:02
 * @Description
 */
@Data
public class SysMenuVO {

    /**
     * 主键Id
     */
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
}
