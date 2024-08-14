package com.carole.secure.toolbox.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2024/7/27 13:09
 * @Description
 */
@Data
@Builder
public class DataTableDTO {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 注释
     */
    private String remark;

}
