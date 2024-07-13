package com.carole.secure.common.result;

import com.carole.secure.common.type.StatusType;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/9/14 21:19
 * @Description
 */
@Data
public class Result<T> {

    /**
     * 状态code
     */
    private Integer code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 成功
     */
    public Result(T data, Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 失败
     */
    public Result(String msg, Integer code) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 失败
     */
    public Result(String msg) {
        this.msg = msg;
    }

    /**
     * 方便静态调用(成功)
     */
    public static <T> Result<T> success(T data, Integer code, String msg) {
        return new Result<T>(data, code, msg);
    }

    /**
     * 方便静态调用(成功)
     */

    public static <T> Result<T> success(T data, StatusType statusType) {
        return new Result<T>(data, statusType.getCode(), statusType.getMsg());
    }

    /**
     * 方便静态调用(成功)
     */
    public static <T> Result<T> success(StatusType statusType) {
        return new Result<T>(null, statusType.getCode(), statusType.getMsg());
    }

    /**
     * 方便静态调用(失败)
     */
    public static <T> Result<T> failed(String msg, Integer code) {
        return new Result<T>(msg, code);
    }

    /**
     * 方便静态调用(失败)
     */
    public static <T> Result<T> failed(String msg) {
        return new Result<T>(msg, StatusType.ERROR.getCode());
    }

    /**
     * 方便静态调用(失败)
     */
    public static <T> Result<T> failed(StatusType statusType) {
        return new Result<T>(statusType.getMsg(), statusType.getCode());
    }
}
