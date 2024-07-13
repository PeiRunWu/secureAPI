package com.carole.secure.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author CaroLe
 * @Date 2023/10/15 0:02
 * @Description
 */
@Mapper
public interface SysRoleMenuMapper {
    /**
     * 通过角色Id删除角色菜单关联表
     *
     * @param roleId 角色Id
     */
    void deleteByRoleId(@Param("roleId") String roleId);

    /**
     * 查找当前角色下的所属菜单
     *
     * @param roleId 角色Id
     * @return List
     */
    List<String> queryByRoleId(@Param("roleId") String roleId);

    /**
     * 批量插入角色菜单关联表
     * 
     * @param roleId 角色Id
     * @param menuIds 菜单Ids
     */
    void insertBatchMenuIdByRoleId(@Param("roleId") String roleId, @Param("list") List<String> menuIds);

    /**
     * 通过菜单Id检查菜单是否绑定了角色
     * 
     * @param menuId 菜单Id
     * @return boolean
     */
    boolean checkMenuIsBindRoleByMenuId(@Param("menuId") String menuId);
}
