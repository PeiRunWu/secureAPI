package com.carole.secure.auth.service.impl;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.carole.secure.api.feign.pojo.MenuDataDTO;
import com.carole.secure.api.feign.pojo.Result;
import com.carole.secure.api.feign.pojo.SysUser;
import com.carole.secure.api.feign.system.SysUserServiceApi;
import com.carole.secure.auth.model.pojo.UserInfo;
import com.carole.secure.auth.service.UserService;
import com.carole.secure.common.context.SessionContext;
import com.carole.secure.common.exception.DataException;
import com.carole.secure.common.type.ErrorType;
import com.carole.secure.common.util.CommonUaUtil;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.TokenSign;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;

/**
 * @author CaroLe
 * @Date 2023/10/16 22:13
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserServiceApi sysUserServiceApi;

    /**
     * 登入
     *
     * @param userInfo 用户信息
     * @param request 请求信息
     * @return token
     */
    @Override
    public String login(UserInfo userInfo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", userInfo.getUsername());
        map.put("password", userInfo.getPassword());
        Result<SysUser> result = sysUserServiceApi.loginUser(map);
        if (result.getCode() != HttpStatus.SC_OK) {
            throw new DataException(result.getCode(), result.getMsg());
        }
        SysUser sysUser = result.getData();
        StpUtil.login(sysUser.getId());
        // 构造用户登入在线信息
        SaSession tokenSession = StpUtil.getTokenSession();
        tokenSession.set(SessionContext.USER_INFO, sysUser);
        JSONObject jsonObject = generateLoginInfo(sysUser, request);
        tokenSession.set(SessionContext.LOGIN_INFO, jsonObject);
        return StpUtil.getTokenValue();
    }

    /**
     * 注销
     */
    @Override
    public void logout() {
        StpUtil.logoutByTokenValue(StpUtil.getTokenValue());
    }

    /**
     * 获取当前用户登入信息
     *
     * @return SysUser
     */
    @Override
    public SysUser queryCurrentUser() {
        SaSession tokenSession = StpUtil.getTokenSession();
        return (SysUser)tokenSession.get(SessionContext.USER_INFO);
    }

    /**
     * 获取当前用户下所属的菜单列表
     *
     * @return List
     */
    @Override
    public List<Tree<String>> getCurrentUserMenus() {
        String userId = StpUtil.getLoginIdAsString();
        Result<List<MenuDataDTO>> result = sysUserServiceApi.getCurrentUserMenus(userId);
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setChildrenKey("routes");
        return TreeUtil.build(result.getData(), "0", treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getOrderNum());
            tree.setName(treeNode.getName());
            tree.putExtra("path", treeNode.getPath());
            tree.putExtra("icon", treeNode.getIcon());
            tree.putExtra("hideInMenu", treeNode.getHideInMenu() != 1);
        });
    }

    /**
     * 获取用户在线人数信息
     *
     * @return List
     */
    @Override
    public List<JSONObject> getUserOnlineInfo() {
        List<String> sessionIdList = StpUtil.searchSessionId("", 0, -1, false);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (String sessionId : sessionIdList) {
            SaSession session = StpUtil.getSessionBySessionId(sessionId);
            List<TokenSign> tokenSignList = session.getTokenSignList();
            for (TokenSign tokenSign : tokenSignList) {
                String token = tokenSign.getValue();
                SaSession tokenSession = StpUtil.getTokenSessionByToken(token);
                JSONObject jsonObject = (JSONObject)tokenSession.get(SessionContext.LOGIN_INFO);
                if (ObjectUtil.isNotNull(jsonObject)) {
                    jsonObjectList.add(jsonObject);
                }
            }
        }
        return jsonObjectList;
    }

    /**
     * 下线用户
     *
     * @param id 用户Id
     */
    @Override
    public void offlineUser(String id) {
        StpUtil.kickout(id);
    }

    private JSONObject generateLoginInfo(SysUser sysUse, HttpServletRequest request) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", sysUse.getId());
            jsonObject.put("username", sysUse.getUsername());
            jsonObject.put("hostAddress", localHost.getHostAddress());
            jsonObject.put("hostName", localHost.getHostName());
            jsonObject.put("browser", CommonUaUtil.getBrowser(request));
            jsonObject.put("os", CommonUaUtil.getOs(request));
            jsonObject.put("loginTime",
                LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_PATTERN));
            return jsonObject;
        } catch (Exception e) {
            throw new DataException(ErrorType.SERVICE_ERROR);
        }
    }
}
