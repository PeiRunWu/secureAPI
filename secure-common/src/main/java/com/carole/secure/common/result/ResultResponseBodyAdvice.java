package com.carole.secure.common.result;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.carole.secure.common.exception.DataException;
import com.carole.secure.common.type.ErrorType;
import com.carole.secure.common.type.SuccessType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;

/**
 * @author CaroLe
 * @Date 2023/9/14 21:31
 * @Description
 */
@RestControllerAdvice
public class ResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType,
        @NonNull MediaType selectedContentType, @NonNull Class selectedConverterType,
        @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        // 防止重复包裹的问题出现
        if (body instanceof Result) {
            return body;
        }
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(Result.success(body, SuccessType.SUCCESS));
            } catch (JsonProcessingException e) {
                throw new DataException(ErrorType.PARSING_ERROR);
            }
        }
        return Result.success(body, SuccessType.SUCCESS);
    }
}
