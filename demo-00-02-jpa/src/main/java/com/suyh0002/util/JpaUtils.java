package com.suyh0002.util;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JpaUtils {
    /**
     * 对类对象的字符串属性进行精确匹配数据库查询的匹配器
     *
     * @param clazz 实体类对象，使用@Entity 注解过的类对象
     * @param <T>   泛型
     * @return 精确匹配器
     * @deprecated 查看makeQueryExampleExact
     */
    @Deprecated
    public static <T> ExampleMatcher makeStringQueryExact(Class<T> clazz) {
        return makeStringFieldsQuery(clazz, ExampleMatcher.GenericPropertyMatchers.exact());
    }

    /**
     * 对类对象的字符串属性进行模糊匹配数据库查询的匹配器
     *
     * @param clazz 实体类对象，使用@Entity 注解过的类对象
     * @param <T>   泛型
     * @return 模糊匹配器
     * @deprecated 查看makeQueryExampleLike
     */
    @Deprecated
    public static <T> ExampleMatcher makeStringQueryLike(Class<T> clazz) {
        // 模糊匹配忽略大小写
        return makeStringFieldsQuery(clazz, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
    }

    /**
     * 给定类对象，进行字符串字段的所有字段组合匹配器
     * <p>
     * 刚测试了一下，没有做特别处理的匹配器，是完全相等的匹配处理
     *
     * @param clazz         实体类对象，使用@Entity 注解过的类对象
     * @param stringMatcher 字符串匹配器
     * @param <T>           泛型
     * @return 匹配器
     * @deprecated 查看：makeEntityExample
     */
    @Deprecated
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

    /**
     * 精确匹配查询
     *
     * @param entityExact
     * @param includeNullFields
     * @param <T>
     * @return
     */
    public static <T> Example<T> makeQueryExampleExact(T entityExact, Collection<String> includeNullFields) {
        return makeEntityExample(entityExact, includeNullFields, ExampleMatcher.GenericPropertyMatchers.exact());
    }

    /**
     * 模糊查询
     *
     * @param entityLike
     * @param includeNullFields
     * @param <T>
     * @return
     */
    public static <T> Example<T> makeQueryExampleLike(T entityLike, Collection<String> includeNullFields) {
        return makeEntityExample(entityLike, includeNullFields,
                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
    }

    /**
     * 生成一个example 对象
     * // 使用示例
     * public static <T> List<T> getParentLikeEntityList(T entityLike, PageInfoDto pageInfo) {
     * `  Sort sortBy = Sort.by(Sort.Direction.DESC, "lastUpdateDate");
     * `  PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage() - 1, pageInfo.getPageSize(), sortBy);
     * `  Example<T> exampleLikeQuery = makeQueryExampleLike(entityLike,
     * `  Arrays.asList("parentId"));
     * `  Page<T> resultPage = repository.findAll(exampleLikeQuery, pageRequest);
     * `  pageInfo.setTotalRows((int) resultPage.getTotalElements());
     * `  return resultPage.getContent();
     * }
     *
     * @param entity            条件查询实体对象
     * @param includeNullFields 所在查询IS NULL 对应的属性
     * @param strMatcher        字符串匹配器
     * @param <T>               泛型，这里有个问题就是对于子类类型似乎JPA 处理不了。只能是@Entity 标注的类，且没有子类。或者对象不能是子类
     * @return example
     */
    public static <T> Example<T> makeEntityExample(
            T entity, Collection<String> includeNullFields,
            ExampleMatcher.GenericPropertyMatcher strMatcher) {
        if (includeNullFields == null) {
            includeNullFields = new ArrayList<>();
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Field[] fields = entity.getClass().getDeclaredFields();
        List<String> ignoreField = new ArrayList<>();  // 需要忽略 IS NULL 的 SQL 语句的属性
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(entity);
                if (fieldValue == null) {
                    // 未指定包含则排除
                    if (!includeNullFields.contains(fieldName)) {
                        ignoreField.add(fieldName);
                    }
                } else {
                    if (field.getType().equals(String.class)) {
                        exampleMatcher = exampleMatcher.withMatcher(fieldName, strMatcher);
                    }
                    // else 对于其他类型做完全匹配处理
                }
            } catch (IllegalAccessException exception) {
                throw new RuntimeException("makeMatcher failed.");
            }
        }

        String[] strs = ignoreField.toArray(new String[ignoreField.size()]);
        // tianjia IS NULL de panduan, yijihuluodiao zhidingpaixudeziduan
        exampleMatcher = exampleMatcher.withIncludeNullValues().withIgnorePaths(strs);

        return Example.of(entity, exampleMatcher);
    }


}
