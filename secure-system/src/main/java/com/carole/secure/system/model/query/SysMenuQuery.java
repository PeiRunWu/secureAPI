package com.carole.secure.system.model.query;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/11 21:42
 * @Description
 */
@Data
public class SysMenuQuery {

    /**
     * 当前页
     */
    private Integer current = 1;

    /**
     * 当前页大小
     */
    private Integer pageSize = 10;

    /**
     * 父Id
     */
    private String parentId;
}
