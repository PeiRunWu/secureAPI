package com.carole.secure.common.handler.field;

import java.lang.reflect.Field;

import cn.hutool.core.util.IdUtil;

/**
 * @author CaroLe
 * @Date 2023/9/16 21:21
 * @Description
 */
public class SnowIdStringHandler extends FieldHandler {
    public SnowIdStringHandler(Field field) {
        super(field);
    }

    /**
     * 处理String类型雪花算法
     *
     * @param field field 字段
     * @param object object对象
     * @throws Exception 异常
     */
    @Override
    void handle(Field field, Object object) throws Exception {
        field.setAccessible(true);
        field.set(object, IdUtil.getSnowflakeNextIdStr());
    }
}
