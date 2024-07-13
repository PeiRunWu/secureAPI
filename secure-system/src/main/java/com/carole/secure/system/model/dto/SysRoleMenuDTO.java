package com.carole.secure.system.model.dto;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/11/4 23:07
 * @Description
 */
@Data
public class SysRoleMenuDTO {

    /**
     * 主键Id
     */
    private String menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型
     */
    private String menuType;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 组件路径
     */
    private String component;
}
