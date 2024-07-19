package com.carole.secure.common.interceptor;

import static com.carole.secure.common.context.BaseContext.INSERT;
import static com.carole.secure.common.context.BaseContext.UPDATE;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import com.carole.secure.common.annotation.TableField;
import com.carole.secure.common.enums.FieldTypeEnum;
import com.carole.secure.common.enums.OperationSupport;
import com.carole.secure.common.exception.DataException;
import com.carole.secure.common.handler.field.*;
import com.carole.secure.common.type.ErrorType;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2023/9/16 16:33
 * @Description
 */
@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),})
public class TableIdInterceptor implements Interceptor {

    /**
     * 作用是不用每一次一个对象经来都要看下它的哪些属性带有TableId注解; key 为class对象 value 为带有TableId注解的属性
     */
    private final Map<Class<?>, List<FieldHandler>> handlerMap = new ConcurrentHashMap<>();

    /**
     * 存储当前对象操作的类型
     */
    private final Map<Class<?>, String> operationMap = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取方法参数
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement)args[0];
        // 实体对象
        Object entity = args[1];
        String sqlCommandType = mappedStatement.getSqlCommandType().name();
        if (StrUtil.equalsAny(sqlCommandType, INSERT, UPDATE)) {
            process(entity, sqlCommandType);
        }
        return invocation.proceed();
    }

    private void process(Object object, String sqlCommandType) {
        Class<?> objClass = object.getClass();
        List<FieldHandler> handlerList = handlerMap.get(objClass);
        String type = operationMap.get(objClass);
        try {
            SYNC:
            if (CollectionUtil.isEmpty(handlerList) || !StrUtil.equals(type, sqlCommandType)) {
                synchronized (this) {
                    handlerList = handlerMap.get(objClass);
                    type = operationMap.get(objClass);
                    if (CollectionUtil.isNotEmpty(handlerList) && StrUtil.equals(type, sqlCommandType)) {
                        break SYNC;
                    }
                    handlerMap.put(objClass, handlerList = new ArrayList<>());
                    operationMap.put(objClass, sqlCommandType);
                    Field[] allFields = ReflectUtil.getFields(objClass,
                        input -> input != null && input.getAnnotation(TableField.class) != null);
                    for (Field field : allFields) {
                        TableField annotation = field.getAnnotation(TableField.class);
                        OperationSupport operations = annotation.operations();
                        if (operations == OperationSupport.BOTH || operations.name().equals(sqlCommandType)) {
                            handlerList.add(createHandlerForField(field, annotation));
                        }
                    }
                }
            }
            for (FieldHandler handler : handlerList) {
                handler.accept(object);
            }
        } catch (Exception e) {
            throw new DataException(ErrorType.PARSING_ERROR);
        }
    }

    private FieldHandler createHandlerForField(Field field, TableField annotation) {
        if (field.getType().isAssignableFrom(String.class)) {
            switch (annotation.value()) {
                case ASSIGN_ID:
                    return new SnowIdStringHandler(field);
                case ASSIGN_UUID:
                    return new UUIDHandler(field);
                case BCRYPT:
                    return new BcryptHandler(field);
            }
        } else if (field.getType().isAssignableFrom(Long.class)) {
            if (annotation.value() == FieldTypeEnum.ASSIGN_ID) {
                return new SnowIdLongHandler(field);
            }
        }
        throw new DataException(ErrorType.PARSING_ERROR);
    }
}
