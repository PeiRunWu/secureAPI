package com.carole.secure.api.feign.pojo;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/11/5 22:37
 * @Description
 */
@Data
public class MenuDataDTO {
    /**
     * 菜单Id
     */
    private String id;

    /**
     * 父Id
     */
    private String parentId;

    /**
     * 路径
     */
    private String path;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 是否隐藏
     */
    private Integer hideInMenu;
}
