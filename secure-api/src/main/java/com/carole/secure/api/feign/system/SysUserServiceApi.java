package com.carole.secure.api.feign.system;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.carole.secure.api.feign.pojo.MenuDataDTO;
import com.carole.secure.api.feign.pojo.Result;
import com.carole.secure.api.feign.pojo.SysUser;

/**
 * @author CaroLe
 * @Date 2023/10/16 22:40
 * @Description
 */
@FeignClient(value = "secure-system", contextId = "SysUserService")
public interface SysUserServiceApi {

    /**
     * 用户表单登入
     *
     * @param map 用户信息
     * @return 用户Id
     */
    @PostMapping("/sysUser/loginUser")
    Result<SysUser> loginUser(@RequestBody Map<String, Object> map);

    /**
     * 获取当前用户下所属按钮权限列表
     *
     * @param id 用户Id
     * @return List
     */
    @GetMapping("/sysUser/getPermissionList")
    Result<List<String>> getPermissionList(@RequestParam("id") String id);

    /**
     * 获取当前用户下所属的菜单列表
     *
     * @param id 用户Id
     * @return List
     */
    @GetMapping("/sysUser/getCurrentUserMenus")
    Result<List<MenuDataDTO>> getCurrentUserMenus(@RequestParam("id") String id);
}
