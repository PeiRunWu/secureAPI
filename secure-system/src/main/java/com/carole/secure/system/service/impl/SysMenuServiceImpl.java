package com.carole.secure.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carole.secure.common.exception.DataException;
import com.carole.secure.common.type.ErrorType;
import com.carole.secure.common.util.PageUtil;
import com.carole.secure.system.mapper.SysMenuMapper;
import com.carole.secure.system.mapper.SysRoleMenuMapper;
import com.carole.secure.system.model.dto.AssignAuthMenuDTO;
import com.carole.secure.system.model.dto.SysMenuDTO;
import com.carole.secure.system.model.pojo.SysMenu;
import com.carole.secure.system.model.query.SysMenuQuery;
import com.carole.secure.system.model.vo.SysMenuVO;
import com.carole.secure.system.service.SysMenuService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author CaroLe
 * @Date 2023/10/11 21:43
 * @Description
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 分页获取菜单目录列表
     *
     * @param sysMenuQuery 分页信息
     * @return PageUtil
     */
    @Override
    public PageUtil<SysMenuDTO> getMenuInfoByPage(SysMenuQuery sysMenuQuery) {
        return PageUtil.startPage(sysMenuQuery.getCurrent(), sysMenuQuery.getPageSize(),
            () -> sysMenuMapper.getMenuInfoByPage(sysMenuQuery));
    }

    /**
     * 获取树形菜单列表
     *
     * @return List
     */
    @Override
    public List<Tree<String>> getMenuTableInfo() {
        List<SysMenu> menuTableInfo = sysMenuMapper.getMenuTableInfo();
        if (CollectionUtil.isNotEmpty(menuTableInfo)) {
            menuTableInfo = menuTableInfo.stream().filter(item -> !StrUtil.equals(item.getMenuType(), "F"))
                .collect(Collectors.toList());
        }
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setNameKey("title");
        treeNodeConfig.setIdKey("value");
        return TreeUtil.build(menuTableInfo, "0", treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getOrderNum());
            tree.setName(treeNode.getMenuName());
        });
    }

    /**
     * 查找当前角色下的菜单列表
     *
     * @param roleId 角色Id
     * @return AssignAuthMenuDTO
     */
    @Override
    public AssignAuthMenuDTO findSysMenuByRoleId(String roleId) {
        AssignAuthMenuDTO assignAuthMenuDTO = new AssignAuthMenuDTO();
        // 配置树形列表
        List<SysMenu> menuTableInfo = sysMenuMapper.getMenuTableInfo();
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setNameKey("title");
        treeNodeConfig.setIdKey("key");
        List<Tree<String>> treeList = TreeUtil.build(menuTableInfo, "0", treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getOrderNum());
            tree.setName(treeNode.getMenuName());
            tree.putExtra("disabled", treeNode.getStatus() != 1);
        });
        assignAuthMenuDTO.setTreeList(treeList);
        // 查找当前角色下的所属菜单
        List<String> menuIds = sysRoleMenuMapper.queryByRoleId(roleId);
        assignAuthMenuDTO.setMenuIds(menuIds);
        return assignAuthMenuDTO;
    }

    /**
     * 创建菜单列表信息
     *
     * @param sysMenuVO 菜单列表信息
     */
    @Override
    @Transactional(rollbackFor = DataException.class)
    public void createOrUpdateMenuInfo(SysMenuVO sysMenuVO) {
        if (StrUtil.isEmpty(sysMenuVO.getId())) {
            SysMenu sysMenu = new SysMenu();
            // 查询菜单名称是否已经存在
            if (sysMenuMapper.checkMenuNameIsExist(sysMenuVO.getMenuName())) {
                throw new DataException(ErrorType.MENU_NAME_IS_EXIST);
            }
            BeanUtil.copyProperties(sysMenuVO, sysMenu);
            sysMenuMapper.insert(sysMenu);
        } else {
            // 更新
            SysMenu sysMenu = new SysMenu();
            BeanUtil.copyProperties(sysMenuVO, sysMenu);
            sysMenuMapper.updateMenuInfo(sysMenu);
        }
    }

    /**
     * 删除菜单信息
     *
     * @param id 菜单Id
     */
    @Override
    @Transactional(rollbackFor = DataException.class)
    public void deleteMenuInfo(String id) {
        // 先查找是否绑定了角色
        if (sysRoleMenuMapper.checkMenuIsBindRoleByMenuId(id)) {
            throw new DataException(ErrorType.MENU_BIND_ROLE);
        }
        // 查询是否有子菜单
        if (sysMenuMapper.checkMenuHasChildren(id)) {
            throw new DataException(ErrorType.MENU_HAS_CHILDREN);
        }
        sysMenuMapper.delete(id);
    }
}
