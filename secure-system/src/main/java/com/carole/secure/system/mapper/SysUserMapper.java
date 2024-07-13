package com.carole.secure.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carole.secure.api.feign.pojo.MenuDataDTO;
import com.carole.secure.system.model.dto.SysUserDTO;
import com.carole.secure.system.model.pojo.SysUser;
import com.carole.secure.system.model.query.SysUserQuery;

/**
 * @author CaroLe
 * @Date 2023/9/14 21:11
 * @Description
 */
@Mapper
public interface SysUserMapper {
    /**
     * 检查用户名是否已经存在
     * 
     * @param username 用户名
     * @return true或false
     */
    boolean checkUsernameIsExist(@Param("username") String username);

    /**
     * 插入用户信息
     * 
     * @param sysUser 用户信息
     */
    void insert(@Param("param") SysUser sysUser);

    /**
     * 分页查询用户信息
     *
     * @param searchQuery 分页条件信息
     * @return List
     */
    List<SysUserDTO> getUserInfoByPage(@Param("param") SysUserQuery searchQuery);

    /**
     * 编辑用户信息
     *
     * @param sysUser 用户信息
     */
    void updateUserInfo(@Param("param") SysUser sysUser);

    /**
     * 删除用户信息
     *
     * @param id 用户Id
     */
    void deleteUserInfoById(@Param("id") String id);

    /**
     * 根据用户名称查询用户信息
     * 
     * @param username 用户名称
     * @return SysUser
     */
    SysUser querySysUserInfoByUsername(@Param("username") String username);

    /**
     * 获取当前用户下所属按钮权限列表
     *
     * @param id 用户Id
     * @return List
     */
    List<String> getPermissionList(@Param("id") String id);

    /**
     * 获取当前用户下所属的菜单列表
     *
     * @param id 用户Id
     * @return List
     */
    List<MenuDataDTO> getCurrentUserMenus(@Param("id") String id);
}
