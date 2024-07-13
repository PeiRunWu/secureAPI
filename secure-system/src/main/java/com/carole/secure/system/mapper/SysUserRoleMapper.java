package com.carole.secure.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author CaroLe
 * @Date 2023/10/15 22:23
 * @Description
 */
@Mapper
public interface SysUserRoleMapper {
    /**
     * 插入用户角色关联表
     * 
     * @param userId 用户Id
     * @param roleIds 角色Ids
     */
    void insertBatchRoleIdsByUserId(@Param("userId") String userId, @Param("list") List<String> roleIds);

    /**
     * 通过用户Id删除用户角色关联信息
     * 
     * @param userId 用户Id
     */
    void deleteByUserId(@Param("userId") String userId);

    /**
     * 检查用户关联表是否存在绑定角色
     * 
     * @param roleId 角色Id
     * @return boolean
     */
    boolean checkUserIsBindRoleByRoleId(@Param("roleId") String roleId);
}
