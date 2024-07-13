package com.carole.secure.common.exception;


import com.carole.secure.common.type.StatusType;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:41
 * @Description
 */
public class DataException extends BaseException {
    public DataException(Integer code, String msg) {
        super(code, msg);
    }

    public DataException(StatusType statusType) {
        super(statusType);
    }

}
