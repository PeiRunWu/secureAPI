package com.carole.secure.gateway.client;

import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.carole.secure.api.feign.pojo.Result;
import com.carole.secure.api.feign.system.SysUserServiceApi;

/**
 * @author CaroLe
 * @Date 2023/11/5 12:54
 * @Description
 */
@Component
public class ClientHolder {

    @Lazy
    @Resource
    private SysUserServiceApi sysUserServiceApi;

    @Async
    public Future<List<String>> getPermissionList(Object loginId) {
        Result<List<String>> result = sysUserServiceApi.getPermissionList(String.valueOf(loginId));
        return new AsyncResult<>(result.getData());
    }

}
