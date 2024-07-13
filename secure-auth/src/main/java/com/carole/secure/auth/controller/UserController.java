package com.carole.secure.auth.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.carole.secure.api.feign.pojo.SysUser;
import com.carole.secure.auth.model.pojo.UserInfo;
import com.carole.secure.auth.service.UserService;

import cn.hutool.core.lang.tree.Tree;

/**
 * @author CaroLe
 * @Date 2023/10/16 22:09
 * @Description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 登入
     *
     * @param userInfo 用户信息
     * @param request  请求信息
     * @return token
     */
    @PostMapping("/login")
    public String login(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        return userService.login(userInfo, request);
    }

    /**
     * 注销
     */
    @GetMapping("/logout")
    public void logout() {
        userService.logout();
    }

    /**
     * 获取当前用户登入信息
     *
     * @return SysUser
     */
    @GetMapping("/queryCurrentUser")
    public SysUser queryCurrentUser() {
        return userService.queryCurrentUser();
    }

    /**
     * 获取当前用户下所属的菜单列表
     *
     * @return List
     */
    @GetMapping("/getCurrentUserMenus")
    public List<Tree<String>> getCurrentUserMenus() {
        return userService.getCurrentUserMenus();
    }

    /**
     * 获取用户在线人数信息
     *
     * @return List
     */
    @GetMapping("/getUserOnlineInfo")
    public List<JSONObject> getUserOnlineInfo() {
        return userService.getUserOnlineInfo();
    }

    /**
     * 下线用户
     *
     * @param id 用户Id
     */
    @GetMapping("/offlineUser")
    public void offlineUser(@RequestParam("id") String id) {
        userService.offlineUser(id);
    }

}
