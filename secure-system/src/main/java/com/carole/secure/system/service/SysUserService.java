package com.carole.secure.system.service;

import java.util.List;
import java.util.Map;

import com.carole.secure.api.feign.pojo.MenuDataDTO;
import com.carole.secure.common.result.Result;
import com.carole.secure.common.util.PageUtil;
import com.carole.secure.system.model.dto.SysUserDTO;
import com.carole.secure.system.model.pojo.SysUser;
import com.carole.secure.system.model.query.SysUserQuery;
import com.carole.secure.system.model.vo.SysUserVO;

/**
 * @author CaroLe
 * @Date 2023/9/14 21:15
 * @Description
 */
public interface SysUserService {
    /**
     * 创建/编辑用户
     *
     * @param sysUserVO 用户信息
     */
    void createOrUpdateUserInfo(SysUserVO sysUserVO);

    /**
     * 分页查询用户信息
     *
     * @param searchQuery 分页条件信息
     * @return PageUtil
     */
    PageUtil<SysUserDTO> getUserInfoByPage(SysUserQuery searchQuery);

    /**
     * 删除用户信息
     *
     * @param id 用户Id
     */
    void deleteUserInfo(String id);

    /**
     * 用户表单登入
     *
     * @param map 用户信息
     * @return 用户信息
     */
    Result<SysUser> loginUser(Map<String, Object> map);

    /**
     * 获取当前用户下所属按钮权限列表
     *
     * @param id 用户Id
     * @return List
     */
    List<String> getPermissionList(String id);

    /**
     * 获取当前用户下所属的菜单列表
     *
     * @param id 用户Id
     * @return List
     */
    List<MenuDataDTO> getCurrentUserMenus(String id);
}
