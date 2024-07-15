package com.carole.secure.api.feign.system.fallbcak;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.carole.secure.api.feign.pojo.MenuDataDTO;
import com.carole.secure.api.feign.pojo.Result;
import com.carole.secure.api.feign.pojo.SysUser;
import com.carole.secure.api.feign.system.SysUserServiceApi;

import cn.hutool.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2024/7/15 13:55
 * @Description
 */
@Slf4j
@Component
public class SysUserFallbackFactory implements FallbackFactory<SysUserServiceApi> {

    @Override
    public SysUserServiceApi create(Throwable cause) {
        return new SysUserServiceApi() {
            @Override
            public Result<SysUser> loginUser(Map<String, Object> map) {
                log.error("登入用户服务远程接口熔断:{}", cause.getMessage(), cause);
                return new Result<>(HttpStatus.HTTP_INTERNAL_ERROR, "登入用户服务远程接口异常", null);
            }

            @Override
            public Result<List<String>> getPermissionList(String id) {
                log.error("获取用户url权限服务远程接口熔断:{}", cause.getMessage(), cause);
                return new Result<>(HttpStatus.HTTP_INTERNAL_ERROR, "获取用户url权限服务远程接口异常", null);

            }

            @Override
            public Result<List<MenuDataDTO>> getCurrentUserMenus(String id) {
                log.error("获取用户菜单权限服务远程接口熔断:{}", cause.getMessage(), cause);
                return new Result<>(HttpStatus.HTTP_INTERNAL_ERROR, "获取用户菜单权限服务远程接口异常", null);

            }
        };
    }
}
