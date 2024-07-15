package com.carole.secure.elasticsearch.aop;


import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.carole.secure.common.context.ElasticContext;
import com.carole.secure.common.exception.DataException;
import com.carole.secure.common.type.ErrorType;
import com.carole.secure.elasticsearch.annotation.OperationLog;
import com.carole.secure.elasticsearch.config.ElasticSearchConfig;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2023/9/17 16:09
 * @Description
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Pointcut("@annotation(com.carole.secure.elasticsearch.annotation.OperationLog)")
    public void pointcut() {}

    @AfterReturning(value = "pointcut()")
    public void handleOperationLog(JoinPoint joinPoint) {
        try {
            // 获取请求地址
            HttpServletRequest request =
                ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                    .getRequest();
            // 获取方法签名
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
            // 获取方法
            Method method = methodSignature.getMethod();
            if (method.isAnnotationPresent(OperationLog.class)) {
                // 获取方法上的元注解
                OperationLog annotation = methodSignature.getMethod().getAnnotation(OperationLog.class);
                if (annotation != null) {
                    InetAddress localHost = InetAddress.getLocalHost();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("operaModule", annotation.operaModule());
                    jsonObject.put("operaDesc", annotation.operaDesc());
                    jsonObject.put("requestUri", request.getRequestURI());
                    jsonObject.put("requestMethod", request.getMethod());
                    jsonObject.put("methodName", joinPoint.getSignature().getName());
                    jsonObject.put("hostAddress", localHost.getHostAddress());
                    jsonObject.put("hostName", localHost.getHostName());
                    jsonObject.put("innerIP", NetUtil.isInnerIP(localHost.getHostAddress()));
                    jsonObject.put("createTime", DateUtil.now());
                    IndexRequest indexRequest = new IndexRequest(ElasticContext.SYS_OPERATION_LOG_INDEX);
                    indexRequest.id(IdUtil.getSnowflakeNextIdStr());
                    indexRequest.source(jsonObject, XContentType.JSON);
                    restHighLevelClient.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
                }
            }
        } catch (Exception e) {
            log.error("日志保存es错误:{}", e.getMessage());
            throw new DataException(ErrorType.ES_LOG_FAILED);
        }
    }

}
