package com.carole.secure.common.annotation;

import java.lang.annotation.*;

import com.carole.secure.common.enums.OperaModuleEnum;

/**
 * @author CaroLe
 * @Date 2023/9/17 15:56
 * @Description 日志
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLog {

    /**
     * 操作描述
     */
    String operaDesc() default "";

    /**
     * 操作模块
     */
    OperaModuleEnum operaModule() default OperaModuleEnum.SYSTEM_USER;

}
