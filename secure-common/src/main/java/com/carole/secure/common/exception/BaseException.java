package com.carole.secure.common.exception;


import com.carole.secure.common.type.StatusType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:41
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Integer code;

    private String msg;

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(StatusType statusType) {
        super(statusType.getMsg());
        this.msg = statusType.getMsg();
        this.code = statusType.getCode();
    }
}

