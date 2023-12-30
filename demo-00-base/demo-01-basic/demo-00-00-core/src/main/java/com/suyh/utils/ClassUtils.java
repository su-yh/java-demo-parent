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

            try {
                field.setAccessible(true);  // 对私有属性打开允许访问标志
                Object fieldValue = field.get(source);
                field.set(target, fieldValue);
            } catch (IllegalAccessException e) {
                log.error("exception", e);
                throw new RuntimeException("propertyInject failed");
            }
        }
    }

    // TODO: suyh - 忽然发现这个方法似乎有些问题。
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
                    field.set(entity, null);
                    continue;
                }

                final Object newInstance = fieldValue.getClass().newInstance();
                field.set(entity, newInstance);

                // 基本类型
                if (newInstance instanceof Integer
                        || newInstance instanceof Boolean
                        || newInstance instanceof Long
                        || newInstance instanceof Double
                        || newInstance instanceof Float
                        || newInstance instanceof Date
                        || newInstance instanceof Byte) {
                    continue;
                }

                if (newInstance instanceof String) {
                    String strValue = ((String) newInstance).trim();
                    field.set(entity, strValue);
                    log.info("field trim, class: {}, name: {} value: {}, type: {}",
                            clazz.getSimpleName(), field.getName(), field.get(entity), field.getType());
                } else if (fieldValue instanceof Map) {
                    log.info("map 怎么处理呢？");
                } else if (fieldValue instanceof Collection) {
                    Collection<Object> collObject = (Collection<Object>) fieldValue;
                    Collection<Object> newCollObject = (Collection<Object>) newInstance;
                    for (Object obj : collObject) {
                        final Object newObj = obj.getClass().newInstance();
                        newCollObject.add(newObj);
                        strTrim(newObj, newObj.getClass());
                    }
                } else {
                    // 剩下的应该就是自定义类型了吧
                    strTrim(newInstance, newInstance.getClass());
                }
            } catch (IllegalAccessException | InstantiationException e) {
                log.warn("strTrim failed", e);
            }
        }

        // 父类中的字段属性
        strTrim(entity, clazz.getSuperclass());
    }
}
