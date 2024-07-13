package com.carole.secure.common.exception;

import java.util.Objects;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.carole.secure.common.result.Result;
import com.carole.secure.common.type.ErrorType;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:41
 * @Description
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(BaseException.class)
    public Result<String> baseExceptionHandle(BaseException exception) {
        return Result.failed(exception.getMessage(), exception.getCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidException(MethodArgumentNotValidException e) {
        return Result.failed(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandle(Exception exception) {
        log.error("Exception 错误:", exception);
        return Result.failed(ErrorType.SERVICE_ERROR);
    }

    @ExceptionHandler(NotLoginException.class)
    public Result<String> notLoginExceptionHandle(NotLoginException exception) {
        log.error("Exception 错误:", exception);
        return Result.failed(exception.getMessage(), ErrorType.SERVICE_ERROR.getCode());
    }
}