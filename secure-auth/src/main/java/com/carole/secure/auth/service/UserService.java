package com.carole.secure.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.carole.secure.api.feign.pojo.SysUser;
import com.carole.secure.auth.model.pojo.UserInfo;

import cn.hutool.core.lang.tree.Tree;

/**
 * @author CaroLe
 * @Date 2023/10/16 22:13
 * @Description
 */
public interface UserService {

    /**
     * 登入
     *
     * @param userInfo 用户信息
     * @param request 请求信息
     * @return token
     */
    String login(UserInfo userInfo, HttpServletRequest request);

    /**
     * 注销
     */
    void logout();

    /**
     * 获取当前用户登入信息
     *
     * @return SysUser
     */
    SysUser queryCurrentUser();

    /**
     * 获取当前用户下所属的菜单列表
     *
     * @return List
     */
    List<Tree<String>> getCurrentUserMenus();

    /**
     * 获取用户在线人数信息
     *
     * @return List
     */
    List<JSONObject> getUserOnlineInfo();

    /**
     * 下线用户
     *
     * @param id 用户Id
     */
    void offlineUser(String id);
}
