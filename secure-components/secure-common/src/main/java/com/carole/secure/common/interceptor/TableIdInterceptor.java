package com.carole.secure.common.interceptor;

import static com.carole.secure.common.context.BaseContext.INSERT;

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

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取方法参数
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement)args[0];
        // 实体对象
        Object entity = args[1];
        if (StrUtil.equals(INSERT, mappedStatement.getSqlCommandType().name())) {
            // 将实体存入Set
            Set<Object> entitySet = getEntity(entity);
            for (Object object : entitySet) {
                process(object);
            }
        }
        return invocation.proceed();
    }

    private void process(Object object) {
        Class<?> objClass = object.getClass();
        List<FieldHandler> handlerList = handlerMap.get(objClass);
        try {
            SYNC:
            if (handlerList == null) {
                synchronized (this) {
                    handlerList = handlerMap.get(objClass);
                    if (CollectionUtil.isNotEmpty(handlerList)) {
                        break SYNC;
                    }
                    handlerMap.put(objClass, handlerList = new ArrayList<>());
                    Field[] allFields = ReflectUtil.getFields(objClass,
                        input -> input != null && input.getAnnotation(TableField.class) != null);
                    for (Field field : allFields) {
                        TableField annotation = field.getAnnotation(TableField.class);
                        if (field.getType().isAssignableFrom(String.class)) {
                            if (annotation.value().equals(FieldTypeEnum.ASSIGN_ID)) {
                                handlerList.add(new SnowIdStringHandler(field));
                            } else if (annotation.value().equals(FieldTypeEnum.ASSIGN_UUID)) {
                                handlerList.add(new UUIDHandler(field));
                            } else if (annotation.value().equals(FieldTypeEnum.BCRYPT)) {
                                handlerList.add(new BcryptHandler(field));
                            }
                        } else if (field.getType().isAssignableFrom(Long.class)) {
                            if (annotation.value().equals(FieldTypeEnum.ASSIGN_ID)) {
                                handlerList.add(new SnowIdLongHandler(field));
                            }
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

    /**
     * object是需要插入的实体数据,它可能是对象,也可能是批量插入的对象。 如果是单个对象,那么object就是当前对象
     * 如果是批量插入对象，那么object就是一个map集合,key值为"list",value为ArrayList集合对象
     */
    private Set<Object> getEntity(Object entity) {
        Set<Object> set = new HashSet<>();
        if (entity instanceof Map) {
            Collection<?> values = ((Map<?, ?>)entity).values();
            for (Object value : values) {
                if (value instanceof Collection) {
                    set.addAll((Collection<?>)value);
                } else {
                    set.add(value);
                }
            }
        } else {
            set.add(entity);
        }
        return set;
    }
}
