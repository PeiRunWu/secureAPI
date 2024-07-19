package com.carole.secure.common.annotation;

import java.lang.annotation.*;

import com.carole.secure.common.enums.FieldTypeEnum;
import com.carole.secure.common.enums.OperationSupport;

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

    /**
     * @return 支持的操作类型（默认为支持新增）
     */
    OperationSupport operations() default OperationSupport.INSERT;

}
