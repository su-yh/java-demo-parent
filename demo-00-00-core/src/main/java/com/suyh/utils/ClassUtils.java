package com.suyh.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Slf4j
public class ClassUtils {
    /**
     * 属性注入
     * 对指定类的所有属性从一个对象注入到另一个对象
     * 这个可以用在子类拷贝你类的属性上面很方便
     *
     * @param target 注入目标类对象
     * @param source 属性来源类对象
     * @param clazz  注入属性所属类
     * @param <T>    泛型类
     */
    public static <T> void propertyInject(T target, T source, Class<T> clazz) {
        // 当前类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 排除静态属性
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            Object fieldValue = null;
            try {
                field.setAccessible(true);  // 对私有属性打开允许访问标志
                fieldValue = field.get(source);
                field.set(target, fieldValue);
            } catch (IllegalAccessException e) {
                log.error("exception", e);
                throw new RuntimeException("propertyInject failed");
            }
        }
    }

    public static <T> void strTrim(T entity, Class<?> clazz) {
        if (entity == null || clazz == null) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            try {
                field.setAccessible(true);
                Object fieldValue = field.get(entity);
                if (fieldValue == null) {
                    continue;
                }

                if (fieldValue instanceof String) {
                    String strValue = ((String) fieldValue).trim();
                    field.set(entity, strValue);
                    log.info("field trim, class: {}, name: {} value: {}, type: {}",
                            clazz.getSimpleName(), field.getName(), field.get(entity), field.getType());
                } else if (fieldValue instanceof Map) {
                    Map<Object, Object> mapValue = (Map<Object, Object>) fieldValue;
                    Collection<Object> values = mapValue.values();
                    for (Object obj : values) {
                        strTrim(obj, obj.getClass());
                    }
                } else if (fieldValue instanceof Collection) {
                    Collection<Object> collObject = (Collection<Object>) fieldValue;
                    for (Object obj : collObject) {
                        strTrim(obj, obj.getClass());
                    }
                } else if (fieldValue instanceof Integer
                        || fieldValue instanceof Boolean
                        || fieldValue instanceof Long
                        || fieldValue instanceof Double
                        || fieldValue instanceof Float
                        || fieldValue instanceof Date
                        || fieldValue instanceof Byte) {
                    continue;
                } else {
                    // 剩下的应该就是自定义类型了吧
                    // String fieldValue = (String) field.get(entity);
                    strTrim(fieldValue, fieldValue.getClass());
                }
            } catch (IllegalAccessException e) {
                log.warn("strTrim failed", e);
            }
        }

        // 父类中的字段属性
        strTrim(entity, clazz.getSuperclass());
    }
}
