package com.carole.secure.toolbox.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2024/8/14 15:03
 * @Description
 */
@Data
@Builder
public class DataFiledDTO {

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 类型
     */
    private String typeName;

    /**
     * 字段长度
     */
    private Long columnSize;

    /**
     * 是否自增
     */
    private Boolean autoincrement;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 是否主键
     */
    private Boolean primaryKey;

    /**
     * 注释
     */
    private String remark;
}
