package com.carole.secure.common.handler.field;

import java.lang.reflect.Field;

import cn.hutool.core.util.IdUtil;

/**
 * @author CaroLe
 * @Date 2023/9/16 20:53
 * @Description
 */
public class UUIDHandler extends FieldHandler {
    public UUIDHandler(Field field) {
        super(field);
    }

    /**
     * 处理UUId
     *
     * @param field field 字段
     * @param object object对象
     * @throws Exception 异常
     */
    @Override
    void handle(Field field, Object object) throws Exception {
        field.setAccessible(true);
        field.set(object, IdUtil.fastSimpleUUID());
    }
}
