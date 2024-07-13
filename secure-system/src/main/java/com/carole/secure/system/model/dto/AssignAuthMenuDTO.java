package com.carole.secure.system.model.dto;

import java.util.List;

import cn.hutool.core.lang.tree.Tree;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/14 20:20
 * @Description
 */
@Data
public class AssignAuthMenuDTO {
    /**
     * 选中menu菜单节点
     */
    private List<String> menuIds;

    /**
     * 菜单节点
     */
    private List<Tree<String>> treeList;
}
