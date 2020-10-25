package com.suyh0002.util;

import org.springframework.data.domain.ExampleMatcher;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class JpaUtils {
    /**
     * 对类对象的字符串属性进行精确匹配数据库查询的匹配器
     *
     * @param clazz 实体类对象，使用@Entity 注解过的类对象
     * @param <T>   泛型
     * @return 精确匹配器
     */
    public static <T> ExampleMatcher makeStringQueryExact(Class<T> clazz) {
        return makeStringFieldsQuery(clazz, ExampleMatcher.GenericPropertyMatchers.exact());
    }

    /**
     * 对类对象的字符串属性进行模糊匹配数据库查询的匹配器
     *
     * @param clazz 实体类对象，使用@Entity 注解过的类对象
     * @param <T>   泛型
     * @return 模糊匹配器
     */
    public static <T> ExampleMatcher makeStringQueryLike(Class<T> clazz) {
        // 模糊匹配忽略大小写
        return makeStringFieldsQuery(clazz, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
    }

    /**
     * 给定类对象，进行字符串字段的所有字段组合匹配器
     *
     * 刚测试了一下，没有做特别处理的匹配器，是完全相等的匹配处理
     *
     * @param clazz         实体类对象，使用@Entity 注解过的类对象
     * @param stringMatcher 字符串匹配器
     * @param <T>           泛型
     * @return 匹配器
     */
    public static <T> ExampleMatcher makeStringFieldsQuery(
            Class<T> clazz, ExampleMatcher.GenericPropertyMatcher stringMatcher) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (field.getType().equals(String.class)) {
                exampleMatcher = exampleMatcher.withMatcher(field.getName(), stringMatcher);
            }
        }
        return exampleMatcher;
    }
}
