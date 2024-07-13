package com.carole.secure.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carole.secure.system.model.dto.SysMenuDTO;
import com.carole.secure.system.model.pojo.SysMenu;
import com.carole.secure.system.model.query.SysMenuQuery;

/**
 * @author CaroLe
 * @Date 2023/10/11 21:23
 * @Description
 */
@Mapper
public interface SysMenuMapper {

    /**
     * 分页获取菜单目录列表
     *
     * @param sysMenuQuery 分页信息
     * @return List
     */
    List<SysMenuDTO> getMenuInfoByPage(@Param("param") SysMenuQuery sysMenuQuery);

    /**
     * 获取树形菜单列表
     *
     * @return List
     */
    List<SysMenu> getMenuTableInfo();

    /**
     * 创建菜单列表
     * 
     * @param sysMenu 菜单列表信息
     */
    void insert(@Param("param") SysMenu sysMenu);

    /**
     * 更新菜单列表
     * 
     * @param sysMenu 菜单列表信息
     */
    void updateMenuInfo(@Param("param") SysMenu sysMenu);

    /**
     * 检查菜单名称是否已存在
     *
     * @param menuName 菜单名称
     * @return boolean
     */
    boolean checkMenuNameIsExist(@Param("menuName") String menuName);

    /**
     * 删除菜单信息
     *
     * @param id 菜单Id
     */
    void delete(@Param("id") String id);

    /**
     * 查询是否有子菜单
     * 
     * @param id 菜单Id
     * @return boolean
     */
    boolean checkMenuHasChildren(@Param("id") String id);
}
