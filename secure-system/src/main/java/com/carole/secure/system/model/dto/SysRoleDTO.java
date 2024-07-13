package com.carole.secure.system.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/10 21:39
 * @Description
 */
@Data
public class SysRoleDTO {

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
