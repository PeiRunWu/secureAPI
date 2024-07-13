package com.carole.secure.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carole.secure.system.model.dto.SysRoleDTO;
import com.carole.secure.system.model.pojo.SysRole;
import com.carole.secure.system.model.query.SysRoleQuery;

/**
 * @author CaroLe
 * @Date 2023/10/10 21:35
 * @Description
 */
@Mapper
public interface SysRoleMapper {
    /**
     * 分页查询角色管理信息
     *
     * @param sysRoleQuery 查询条件
     * @return List
     */
    List<SysRoleDTO> getRoleInfoByPage(@Param("param") SysRoleQuery sysRoleQuery);

    /**
     * 检查角色昵称是否已存在
     * 
     * @param roleName 角色昵称
     * @return boolean
     */
    boolean checkRoleNameIsExist(@Param("roleName") String roleName);

    /**
     * 新增角色信息
     * 
     * @param sysRole 角色信息
     */
    void insert(@Param("param") SysRole sysRole);

    /**
     * 更新角色信息
     * 
     * @param sysRole 角色信息
     */
    void updateRoleInfo(@Param("param") SysRole sysRole);

    /**
     * 删除角色信息
     *
     * @param roleId 角色Id
     */
    void delete(@Param("roleId") String roleId);

    /**
     * 获取角色信息排除隐藏角色
     *
     * @return List
     */
    List<SysRoleDTO> getRoleInfoExcludeHidden();
}
