package com.carole.secure.gateway.config;

import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.carole.secure.gateway.client.ClientHolder;

import cn.dev33.satoken.stp.StpInterface;

/**
 * @author CaroLe
 * @Date 2023/11/5 12:16
 * @Description
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Lazy
    @Resource
    private ClientHolder clientHolder;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Future<List<String>> future = clientHolder.getPermissionList(String.valueOf(loginId));
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }

}
