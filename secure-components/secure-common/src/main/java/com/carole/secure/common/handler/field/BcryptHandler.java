package com.carole.secure.common.handler.field;

import java.lang.reflect.Field;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * @author CaroLe
 * @Date 2023/9/16 21:21
 * @Description
 */
public class BcryptHandler extends FieldHandler {

    public BcryptHandler(Field field) {
        super(field);
    }

    /**
     * 处理Bcrypt加密
     *
     * @param field field 字段
     * @param object object对象
     * @throws Exception 异常
     */
    @Override
    void handle(Field field, Object object) throws Exception {
        field.setAccessible(true);
        Object fieldValue = ReflectUtil.getFieldValue(object, field);
        field.set(object, DigestUtil.bcrypt(fieldValue.toString()));
    }
}
