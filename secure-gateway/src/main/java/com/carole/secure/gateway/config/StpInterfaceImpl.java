package com.carole.secure.gateway.config;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.carole.secure.gateway.client.ClientHolder;
import com.carole.secure.redis.context.RedisContext;
import com.carole.secure.redis.util.RedisUtil;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollectionUtil;

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

    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        try {
            String value = redisUtil.get(RedisContext.PERMISSION_KEY + loginId);
            List<String> permissionList = JSON.parseObject(value, new TypeReference<List<String>>() {});
            if (CollectionUtil.isEmpty(permissionList)) {
                Future<List<String>> future = clientHolder.getPermissionList(String.valueOf(loginId));
                redisUtil.setEx(RedisContext.PERMISSION_KEY + loginId, JSON.toJSONString(future.get()),
                    RedisUtil.DEFAULT_EXPIRE, TimeUnit.MILLISECONDS);
                return future.get();
            }
            return permissionList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }

}
