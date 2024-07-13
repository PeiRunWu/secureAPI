package com.carole.secure.common.type;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:38
 * @Description
 */
@Data
@AllArgsConstructor
public class StatusType {

    private Integer code;

    private String msg;

    public static final StatusType SUCCESS = new StatusType(200, "操作成功");

    public static final StatusType ERROR = new StatusType(201, "操作失败");

}
