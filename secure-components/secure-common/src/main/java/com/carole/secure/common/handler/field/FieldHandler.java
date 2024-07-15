package com.carole.secure.common.handler.field;

import java.lang.reflect.Field;

/**
 * @author CaroLe
 * @Date 2023/9/16 20:52
 * @Description
 */
public abstract class FieldHandler {

    Field field;

    FieldHandler(Field field) {
        this.field = field;
    }

    /**
     * 处理
     *
     * @param field field 字段
     * @param object object对象
     * @throws Exception 异常
     */
    abstract void handle(Field field, Object object) throws Exception;

    public void accept(Object o) throws Exception {
        handle(field, o);
    }
}
