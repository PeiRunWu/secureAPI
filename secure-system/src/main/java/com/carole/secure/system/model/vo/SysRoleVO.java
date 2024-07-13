package com.carole.secure.system.model.vo;

import java.util.List;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/10 22:50
 * @Description
 */
@Data
public class SysRoleVO {

    /**
     * 主键Id
     */
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
     * 菜单Id
     */
    private List<String> menuIds;

}
