package com.carole.secure.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.carole.secure.api.feign.pojo.MenuDataDTO;
import com.carole.secure.common.result.Result;
import com.carole.secure.common.util.PageUtil;
import com.carole.secure.elasticsearch.annotation.OperationLog;
import com.carole.secure.elasticsearch.enums.OperaModuleEnum;
import com.carole.secure.system.model.dto.SysUserDTO;
import com.carole.secure.system.model.pojo.SysUser;
import com.carole.secure.system.model.query.SysUserQuery;
import com.carole.secure.system.model.vo.SysUserVO;
import com.carole.secure.system.service.SysUserService;

/**
 * @author CaroLe
 * @Date 2023/9/14 21:14
 * @Description 用户管理
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 创建用户
     * 
     * @param sysUserVO 用户信息
     */
    @PostMapping("/createUserInfo")
    @OperationLog(operaDesc = "创建用户", operaModule = OperaModuleEnum.SYSTEM_USER)
    public void createUserInfo(@RequestBody SysUserVO sysUserVO) {
        sysUserService.createOrUpdateUserInfo(sysUserVO);
    }

    /**
     * 编辑用户
     *
     * @param sysUserVO 用户信息
     */
    @PutMapping("/updateUserInfo")
    @OperationLog(operaDesc = "编辑用户", operaModule = OperaModuleEnum.SYSTEM_USER)
    public void updateUserInfo(@RequestBody SysUserVO sysUserVO) {
        sysUserService.createOrUpdateUserInfo(sysUserVO);
    }

    /**
     * 分页查询用户信息
     * 
     * @param searchQuery 分页条件信息
     * @return PageUtil
     */
    @GetMapping("/getUserInfoByPage")
    public PageUtil<SysUserDTO> getUserInfoByPage(SysUserQuery searchQuery) {
        return sysUserService.getUserInfoByPage(searchQuery);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户Id
     */
    @DeleteMapping("/deleteUserInfo/{id}")
    @OperationLog(operaDesc = "删除用户信息", operaModule = OperaModuleEnum.SYSTEM_USER)
    public void deleteUserInfo(@PathVariable("id") String id) {
        sysUserService.deleteUserInfo(id);
    }

    /**
     * 用户表单登入
     * 
     * @param map 用户信息
     * @return 用户信息
     */
    @PostMapping("/loginUser")
    public Result<SysUser> loginUser(@RequestBody Map<String, Object> map) {
        return sysUserService.loginUser(map);
    }


    /**
     * 获取当前用户下所属按钮权限列表
     * 
     * @param id 用户Id
     * @return List
     */
    @GetMapping("/getPermissionList")
    public List<String> getPermissionList(@RequestParam("id") String id) {
        return sysUserService.getPermissionList(id);
    }

    /**
     * 获取当前用户下所属的菜单列表
     * 
     * @param id 用户Id
     * @return List
     */
    @GetMapping("/getCurrentUserMenus")
    public List<MenuDataDTO> getCurrentUserMenus(@RequestParam("id") String id) {
        return sysUserService.getCurrentUserMenus(id);
    }

}
