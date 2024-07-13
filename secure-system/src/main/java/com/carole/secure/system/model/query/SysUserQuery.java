package com.carole.secure.system.model.query;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/9/16 22:13
 * @Description
 */
@Data
public class SysUserQuery {

    /**
     * 当前页
     */
    private Integer current = 1;

    /**
     * 当前页大小
     */
    private Integer pageSize = 10;

    /**
     * 用户名
     */
    private String username;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

}
