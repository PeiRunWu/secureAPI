package com.carole.secure.system.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/15 0:04
 * @Description
 */
@Data
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 菜单Id
     */
    private String menuId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
