package com.carole.secure.system.model.query;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/10 21:40
 * @Description
 */
@Data
public class SysRoleQuery {

    /**
     * 当前页
     */
    private Integer current = 1;

    /**
     * 当前页大小
     */
    private Integer pageSize = 10;

    /**
     * 角色昵称
     */
    private String roleName;

}
