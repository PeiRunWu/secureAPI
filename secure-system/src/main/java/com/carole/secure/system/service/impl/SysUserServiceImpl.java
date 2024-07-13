package com.carole.secure.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carole.secure.api.feign.pojo.MenuDataDTO;
import com.carole.secure.common.exception.DataException;
import com.carole.secure.common.result.Result;
import com.carole.secure.common.type.ErrorType;
import com.carole.secure.common.type.SuccessType;
import com.carole.secure.common.util.PageUtil;
import com.carole.secure.system.mapper.SysUserMapper;
import com.carole.secure.system.mapper.SysUserRoleMapper;
import com.carole.secure.system.model.dto.SysUserDTO;
import com.carole.secure.system.model.pojo.SysUser;
import com.carole.secure.system.model.query.SysUserQuery;
import com.carole.secure.system.model.vo.SysUserVO;
import com.carole.secure.system.service.SysUserService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * @author CaroLe
 * @Date 2023/9/14 21:15
 * @Description
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 创建/编辑用户
     *
     * @param sysUserVO 用户信息
     */
    @Override
    @Transactional(rollbackFor = DataException.class)
    public void createOrUpdateUserInfo(SysUserVO sysUserVO) {
        if (StrUtil.isEmpty(sysUserVO.getId())) {
            // 插入
            String username = sysUserVO.getUsername();
            // 查询用户名是否已经存在
            if (sysUserMapper.checkUsernameIsExist(username)) {
                throw new DataException(ErrorType.USERNAME_IS_EXIST);
            }
            SysUser sysUser = new SysUser();
            BeanUtil.copyProperties(sysUserVO, sysUser);
            sysUserMapper.insert(sysUser);
            // 插入用户角色关联表
            if (CollectionUtil.isNotEmpty(sysUserVO.getRoleIds())) {
                sysUserRoleMapper.insertBatchRoleIdsByUserId(sysUser.getId(), sysUserVO.getRoleIds());
            }
        } else {
            // 更新
            SysUser sysUser = new SysUser();
            BeanUtil.copyProperties(sysUserVO, sysUser);
            sysUserMapper.updateUserInfo(sysUser);
            // 删除用户角色关联表
            sysUserRoleMapper.deleteByUserId(sysUserVO.getId());
            // 插入用户角色关联表
            if (CollectionUtil.isNotEmpty(sysUserVO.getRoleIds())) {
                sysUserRoleMapper.insertBatchRoleIdsByUserId(sysUser.getId(), sysUserVO.getRoleIds());
            }
        }
    }

    /**
     * 分页查询用户信息
     *
     * @param searchQuery 分页条件信息
     * @return PageUtil
     */
    @Override
    public PageUtil<SysUserDTO> getUserInfoByPage(SysUserQuery searchQuery) {
        return PageUtil.startPage(searchQuery.getCurrent(), searchQuery.getPageSize(),
            () -> sysUserMapper.getUserInfoByPage(searchQuery));
    }

    /**
     * 删除用户信息
     *
     * @param id 用户Id
     */
    @Override
    @Transactional(rollbackFor = DataException.class)
    public void deleteUserInfo(String id) {
        // 删除用户信息
        sysUserMapper.deleteUserInfoById(id);
        // 删除用户角色关联表
        sysUserRoleMapper.deleteByUserId(id);
    }

    /**
     * 用户表单登入
     *
     * @param map 用户信息
     * @return 用户Id
     */
    @Override
    public Result<SysUser> loginUser(Map<String, Object> map) {
        String username = (String)map.get("username");
        String password = (String)map.get("password");
        SysUser sysUser = sysUserMapper.querySysUserInfoByUsername(username);
        if (ObjectUtil.isEmpty(sysUser)) {
            throw new DataException(ErrorType.USER_NAME_NOT_EXIT);
        }
        if (sysUser.getStatus() == 0) {
            throw new DataException(ErrorType.ACCOUNT_STOP);
        }
        if (!DigestUtil.bcryptCheck(password, sysUser.getPassword())) {
            throw new DataException(ErrorType.PASSWORD_ERROR);
        }
        return Result.success(sysUser, SuccessType.SUCCESS);
    }

    /**
     * 获取当前用户下所属按钮权限列表
     *
     * @param id 用户Id
     * @return List
     */
    @Override
    public List<String> getPermissionList(String id) {
        return sysUserMapper.getPermissionList(id);
    }

    /**
     * 获取当前用户下所属的菜单列表
     *
     * @param id 用户Id
     * @return List
     */
    @Override
    public List<MenuDataDTO> getCurrentUserMenus(String id) {
        return sysUserMapper.getCurrentUserMenus(id);
    }
}
