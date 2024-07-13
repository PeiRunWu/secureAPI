package com.carole.secure.system.model.dto;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/15 23:05
 * @Description
 */
@Data
public class SysUserRoleDTO {
    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;
}
