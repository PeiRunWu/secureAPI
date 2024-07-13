package com.carole.secure.system.model.dto;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/14 20:21
 * @Description
 */
@Data
public class DataNodeDTO {

    /**
     * 菜单Id
     */
    private String key;

    /**
     * 菜单名称
     */
    private String title;
}
