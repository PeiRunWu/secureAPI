package com.carole.secure.system.service;

import java.util.List;

import com.carole.secure.common.util.PageUtil;
import com.carole.secure.system.model.dto.SysRoleDTO;
import com.carole.secure.system.model.query.SysRoleQuery;
import com.carole.secure.system.model.vo.SysRoleVO;

/**
 * @author CaroLe
 * @Date 2023/10/10 21:42
 * @Description
 */
public interface SysRoleService {

    /**
     * 分页查询角色管理信息
     *
     * @param sysRoleQuery 查询条件
     * @return PageUtil
     */
    PageUtil<SysRoleDTO> getRoleInfoByPage(SysRoleQuery sysRoleQuery);

    /**
     * 新增/编辑角色信息
     *
     * @param sysRoleVO 角色信息
     */
    void createOrUpdateRoleInfo(SysRoleVO sysRoleVO);

    /**
     * 删除角色信息
     *
     * @param roleId 角色Id
     */
    void deleteRoleInfo(String roleId);

    /**
     * 获取角色信息排除隐藏角色
     *
     * @return List
     */
    List<SysRoleDTO> getRoleInfoExcludeHidden();
}
