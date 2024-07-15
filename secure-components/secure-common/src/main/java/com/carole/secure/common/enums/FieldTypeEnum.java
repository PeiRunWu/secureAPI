package com.carole.secure.common.enums;

/**
 * @author CaroLe
 * @Date 2023/9/16 19:32
 * @Description
 */
public enum FieldTypeEnum {
    /**
     * UUID去掉“-”
     */
    ASSIGN_UUID,
    /**
     * 雪花算法
     */
    ASSIGN_ID,
    /**
     * bcrypt 加密
     */
    BCRYPT
}
