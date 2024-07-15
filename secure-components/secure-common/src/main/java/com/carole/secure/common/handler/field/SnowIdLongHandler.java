package com.carole.secure.common.handler.field;

import java.lang.reflect.Field;

import cn.hutool.core.util.IdUtil;

/**
 * @author CaroLe
 * @Date 2023/9/16 20:54
 * @Description
 */
public class SnowIdLongHandler extends FieldHandler {
    public SnowIdLongHandler(Field field) {
        super(field);
    }

    /**
     * 处理Long类型雪花算法
     *
     * @param field field 字段
     * @param object object对象
     * @throws Exception 异常
     */
    @Override
    void handle(Field field, Object object) throws Exception {
        field.setAccessible(true);
        field.set(object, IdUtil.getSnowflakeNextId());
    }
}
