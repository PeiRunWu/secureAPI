package com.carole.secure.system.model.dto;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/11 21:28
 * @Description
 */
@Data
public class SysMenuDTO {

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

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否有子集
     */
    private Boolean hasChildren;
}
