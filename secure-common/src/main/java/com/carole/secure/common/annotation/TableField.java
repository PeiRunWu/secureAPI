package com.carole.secure.common.annotation;

import java.lang.annotation.*;

import com.carole.secure.common.enums.FieldTypeEnum;

/**
 * @author CaroLe
 * @Date 2023/9/16 16:22
 * @Description 表字段
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableField {

    /**
     * @return 字段类型（默认为雪花算法）
     */
    FieldTypeEnum value() default FieldTypeEnum.ASSIGN_ID;
}
