package com.carole.secure.system.service;

import java.util.List;

import com.carole.secure.common.util.PageUtil;
import com.carole.secure.system.model.dto.AssignAuthMenuDTO;
import com.carole.secure.system.model.dto.SysMenuDTO;
import com.carole.secure.system.model.query.SysMenuQuery;
import com.carole.secure.system.model.vo.SysMenuVO;

import cn.hutool.core.lang.tree.Tree;

/**
 * @author CaroLe
 * @Date 2023/10/11 21:42
 * @Description
 */
public interface SysMenuService {

    /**
     * 分页获取菜单目录列表
     *
     * @param sysMenuQuery 分页信息
     * @return PageUtil
     */
    PageUtil<SysMenuDTO> getMenuInfoByPage(SysMenuQuery sysMenuQuery);

    /**
     * 获取树形菜单列表
     *
     * @return List
     */
    List<Tree<String>> getMenuTableInfo();

    /**
     * 创建菜单列表信息
     *
     * @param sysMenuVO 菜单列表信息
     */
    void createOrUpdateMenuInfo(SysMenuVO sysMenuVO);

    /**
     * 查找当前角色下的菜单列表
     *
     * @param roleId 角色Id
     * @return AssignAuthMenuDTO
     */
    AssignAuthMenuDTO findSysMenuByRoleId(String roleId);

    /**
     * 删除菜单信息
     *
     * @param id 菜单Id
     */
    void deleteMenuInfo(String id);
}
