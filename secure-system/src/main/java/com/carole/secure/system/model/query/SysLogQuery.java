package com.carole.secure.system.model.query;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2024/3/25 21:45
 * @Description
 */
@Data
public class SysLogQuery {

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 当前页大小
     */
    private Integer pageSize;

}
