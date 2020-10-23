package com.suyh.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
}
