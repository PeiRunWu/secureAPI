package com.carole.secure.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.carole.secure.common.util.PageUtil;
import com.carole.secure.elasticsearch.annotation.OperationLog;
import com.carole.secure.elasticsearch.enums.OperaModuleEnum;
import com.carole.secure.system.model.dto.AssignAuthMenuDTO;
import com.carole.secure.system.model.dto.SysMenuDTO;
import com.carole.secure.system.model.query.SysMenuQuery;
import com.carole.secure.system.model.vo.SysMenuVO;
import com.carole.secure.system.service.SysMenuService;

import cn.hutool.core.lang.tree.Tree;

/**
 * @author CaroLe
 * @Date 2023/10/11 21:22
 * @Description 菜单管理
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 分页获取菜单列表
     * 
     * @param sysMenuQuery 分页信息
     * @return PageUtil
     */
    @GetMapping("/getMenuInfoByPage")
    public PageUtil<SysMenuDTO> getMenuInfoByPage(SysMenuQuery sysMenuQuery) {
        return sysMenuService.getMenuInfoByPage(sysMenuQuery);
    }

    /**
     * 获取树形菜单列表
     *
     * @return List
     */
    @GetMapping("/getMenuTableInfo")
    public List<Tree<String>> getMenuTableInfo() {
        return sysMenuService.getMenuTableInfo();
    }

    /**
     * 创建菜单列表信息
     * 
     * @param sysMenuVO 菜单列表信息
     */
    @PostMapping("/createMenuInfo")
    @OperationLog(operaDesc = "创建菜单列表信息", operaModule = OperaModuleEnum.SYSTEM_MENU)
    public void createMenuInfo(@RequestBody SysMenuVO sysMenuVO) {
        sysMenuService.createOrUpdateMenuInfo(sysMenuVO);
    }

    /**
     * 更新菜单列表信息
     *
     * @param sysMenuVO 菜单列表信息
     */
    @PutMapping("/updateMenuInfo")
    @OperationLog(operaDesc = "更新菜单列表信息", operaModule = OperaModuleEnum.SYSTEM_MENU)
    public void updateMenuInfo(@RequestBody SysMenuVO sysMenuVO) {
        sysMenuService.createOrUpdateMenuInfo(sysMenuVO);
    }

    /**
     * 查找当前角色下的菜单列表
     * 
     * @param roleId 角色Id
     * @return AssignAuthMenuDTO
     */
    @GetMapping("/findSysMenuByRoleId")
    public AssignAuthMenuDTO findSysMenuByRoleId(@RequestParam(value = "roleId", required = false) String roleId) {
        return sysMenuService.findSysMenuByRoleId(roleId);
    }

    /**
     * 删除菜单信息
     * 
     * @param id 菜单Id
     */
    @DeleteMapping("/deleteMenuInfo/{id}")
    @OperationLog(operaDesc = "删除菜单信息", operaModule = OperaModuleEnum.SYSTEM_MENU)
    public void deleteMenuInfo(@PathVariable("id") String id) {
        sysMenuService.deleteMenuInfo(id);
    }
}
