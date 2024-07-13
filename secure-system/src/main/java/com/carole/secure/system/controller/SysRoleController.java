package com.carole.secure.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.carole.secure.common.annotation.OperationLog;
import com.carole.secure.common.enums.OperaModuleEnum;
import com.carole.secure.common.util.PageUtil;
import com.carole.secure.system.model.dto.SysRoleDTO;
import com.carole.secure.system.model.query.SysRoleQuery;
import com.carole.secure.system.model.vo.SysRoleVO;
import com.carole.secure.system.service.SysRoleService;

/**
 * @author CaroLe
 * @Date 2023/10/10 21:10
 * @Description 角色管理
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 分页查询角色管理信息
     * 
     * @param sysRoleQuery 查询条件
     * @return PageUtil
     */
    @GetMapping("/getRoleInfoByPage")
    public PageUtil<SysRoleDTO> getRoleInfoByPage(SysRoleQuery sysRoleQuery) {
        return sysRoleService.getRoleInfoByPage(sysRoleQuery);
    }

    /**
     * 新增角色信息
     * 
     * @param sysRoleVO 角色信息
     */
    @PostMapping("/createRoleInfo")
    @OperationLog(operaDesc = "新增角色", operaModule = OperaModuleEnum.SYSTEM_ROLE)
    public void createRoleInfo(@RequestBody SysRoleVO sysRoleVO) {
        sysRoleService.createOrUpdateRoleInfo(sysRoleVO);
    }

    /**
     * 编辑角色信息
     * 
     * @param sysRoleVO 角色信息
     */
    @PutMapping("/updateRoleInfo")
    @OperationLog(operaDesc = "编辑角色", operaModule = OperaModuleEnum.SYSTEM_ROLE)
    public void updateRoleInfo(@RequestBody SysRoleVO sysRoleVO) {
        sysRoleService.createOrUpdateRoleInfo(sysRoleVO);
    }

    /**
     * 删除角色信息
     * 
     * @param roleId 角色Id
     */
    @DeleteMapping("/deleteRoleInfo/{roleId}")
    @OperationLog(operaDesc = "删除角色信息", operaModule = OperaModuleEnum.SYSTEM_ROLE)
    public void deleteRoleInfo(@PathVariable("roleId") String roleId) {
        sysRoleService.deleteRoleInfo(roleId);
    }

    /**
     * 获取角色信息排除隐藏角色
     * 
     * @return List
     */
    @GetMapping("/getRoleInfoExcludeHidden")
    public List<SysRoleDTO> getRoleInfoExcludeHidden() {
        return sysRoleService.getRoleInfoExcludeHidden();
    }

}
