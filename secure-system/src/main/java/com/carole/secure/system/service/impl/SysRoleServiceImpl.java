package com.carole.secure.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carole.secure.common.exception.DataException;
import com.carole.secure.common.type.ErrorType;
import com.carole.secure.common.util.PageUtil;
import com.carole.secure.system.mapper.SysRoleMapper;
import com.carole.secure.system.mapper.SysRoleMenuMapper;
import com.carole.secure.system.mapper.SysUserRoleMapper;
import com.carole.secure.system.model.dto.SysRoleDTO;
import com.carole.secure.system.model.pojo.SysRole;
import com.carole.secure.system.model.query.SysRoleQuery;
import com.carole.secure.system.model.vo.SysRoleVO;
import com.carole.secure.system.service.SysRoleService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author CaroLe
 * @Date 2023/10/10 21:43
 * @Description
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 分页查询角色管理信息
     *
     * @param sysRoleQuery 查询条件
     * @return PageUtil
     */
    @Override
    public PageUtil<SysRoleDTO> getRoleInfoByPage(SysRoleQuery sysRoleQuery) {
        return PageUtil.startPage(sysRoleQuery.getCurrent(), sysRoleQuery.getPageSize(),
            () -> sysRoleMapper.getRoleInfoByPage(sysRoleQuery));
    }

    /**
     * 新增/编辑角色信息
     *
     * @param sysRoleVO 角色信息
     */
    @Override
    @Transactional(rollbackFor = DataException.class)
    public void createOrUpdateRoleInfo(SysRoleVO sysRoleVO) {
        if (StrUtil.isEmpty(sysRoleVO.getId())) {
            // 插入
            String roleName = sysRoleVO.getRoleName();
            // 查询用户名是否已经存在
            if (sysRoleMapper.checkRoleNameIsExist(roleName)) {
                throw new DataException(ErrorType.ROLE_NAME_IS_EXIST);
            }
            // 插入角色表
            SysRole sysRole = new SysRole();
            BeanUtil.copyProperties(sysRoleVO, sysRole);
            sysRoleMapper.insert(sysRole);
            // 插入角色菜单关联表
            if (CollectionUtil.isNotEmpty(sysRoleVO.getMenuIds())) {
                sysRoleMenuMapper.insertBatchMenuIdByRoleId(sysRole.getId(), sysRoleVO.getMenuIds());
            }
        } else {
            // 更新
            SysRole sysRole = new SysRole();
            BeanUtil.copyProperties(sysRoleVO, sysRole);
            sysRoleMapper.updateRoleInfo(sysRole);
            // 先删除角色菜单关联表
            sysRoleMenuMapper.deleteByRoleId(sysRoleVO.getId());
            // 插入角色菜单关联表
            if (CollectionUtil.isNotEmpty(sysRoleVO.getMenuIds())) {
                sysRoleMenuMapper.insertBatchMenuIdByRoleId(sysRole.getId(), sysRoleVO.getMenuIds());
            }
        }
    }

    /**
     * 删除角色信息
     *
     * @param roleId 角色Id
     */
    @Override
    @Transactional(rollbackFor = DataException.class)
    public void deleteRoleInfo(String roleId) {
        // 查询用户角色菜单表
        if (sysUserRoleMapper.checkUserIsBindRoleByRoleId(roleId)) {
            throw new DataException(ErrorType.USER_BIND_ROLE);
        }
        // 删除角色信息表
        sysRoleMapper.delete(roleId);
        // 删除角色菜单关联表
        sysRoleMenuMapper.deleteByRoleId(roleId);
    }

    /**
     * 获取角色信息排除隐藏角色
     *
     * @return List
     */
    @Override
    public List<SysRoleDTO> getRoleInfoExcludeHidden() {
        return sysRoleMapper.getRoleInfoExcludeHidden();
    }
}
