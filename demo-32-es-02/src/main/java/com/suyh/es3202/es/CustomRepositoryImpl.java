package com.suyh.es3202.es;

import com.suyh.es3202.entity.EsQueryRange;
import com.suyh.es3202.repository.CustomRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.TimeZone;

/**
 * 自定义
 *
 * @author 苏雲弘
 * @since 2020-12-04
 */
@Slf4j
public class CustomRepositoryImpl<T, ID> extends SimpleElasticsearchRepository<T, ID> implements CustomRepository<T, ID> {

    public CustomRepositoryImpl(ElasticsearchEntityInformation<T, ID> metadata, ElasticsearchOperations elasticsearchOperations) {
        super(metadata, elasticsearchOperations);
    }

    @Override
    public Page<T> search(T queryObj, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = CustomRepositoryImpl.makeMatchQuery(queryObj);
        return search(boolQueryBuilder, pageable);
    }

    @Override
    public void deleteByQuery(T queryObj) {
        BoolQueryBuilder boolQueryBuilder = CustomRepositoryImpl.makeMatchQuery(queryObj);

        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(boolQueryBuilder);
        deleteQuery.setType(entityInformation.getType());
        deleteQuery.setIndex(entityInformation.getIndexName());

        elasticsearchOperations.delete(deleteQuery);
    }


    /**
     * 生成匹配查询
     *
     * @param entity 查询匹配条件，所有的条件以and 拼接
     * @param <E>    泛型
     * @return elastic search 提供的bool 查询器
     */
    public static <E> BoolQueryBuilder makeMatchQuery(E entity) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        makeMatchQuery(entity, entity.getClass(), boolQueryBuilder);
        return boolQueryBuilder;
    }

    /**
     * 生成匹配查询
     *
     * @param entity           查询匹配条件，按类属性名称进行拼接
     * @param clazz            进行条件拼接的属性所属的类，仅处理该类通过递归处理直到全部处理完成。
     * @param boolQueryBuilder bool 查询构造器
     * @param <E>              泛型
     */
    private static <E> void makeMatchQuery(E entity, Class<?> clazz, BoolQueryBuilder boolQueryBuilder) {
        if (entity == null || clazz == null) {
            return;
        }

        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);

                // 如果配置了 json 别名，则以json别名为准进行处理查询
                Object fieldValue = field.get(entity);
                if (fieldValue == null) {
                    continue;
                }

                makeFieldMustQuery(boolQueryBuilder, field, fieldValue);
            }
        } catch (IllegalAccessException exception) {
            log.error("makeMatchQuery failed.");
            return;
        }

        // 递归处理父类
        makeMatchQuery(entity, clazz.getSuperclass(), boolQueryBuilder);
    }

    /**
     * 对单个字段进行AND 匹配
     *
     * @param boolQueryBuilder bool 匹配器
     * @param field            处理字段
     * @param fieldValue       字段值
     */
    private static void makeFieldMustQuery(BoolQueryBuilder boolQueryBuilder, Field field, Object fieldValue) {
        if (fieldValue instanceof EsQueryRange) {
            // 范围过滤
            EsQueryRange<?> rangeQuery = (EsQueryRange<?>) fieldValue;
            if (!rangeQuery.isValid()) {
                return;
            }
            RangeQueryBuilder queryBuilder = makeRangeQueryBuilder(field, rangeQuery);
            boolQueryBuilder.must(queryBuilder);
        } else {
            // 日期不做等值匹配，就算要做等值匹配这里也是需要像范围查询那样做处理的。
            if (fieldValue instanceof Date) {
                return;
            }
            String esFieldName = getEsFieldName(field);
            MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery(esFieldName, fieldValue);
            boolQueryBuilder.must(queryBuilder);
        }
    }

    /**
     * 从注解中得到ES中的字段别名
     *
     * @param field field
     * @return 字段别名
     */
    private static String getEsFieldName(Field field) {
        String esFieldName = field.getName();
        org.springframework.data.elasticsearch.annotations.Field esField
                = field.getAnnotation(org.springframework.data.elasticsearch.annotations.Field.class);
        if (esField == null) {
            return esFieldName;
        }

        String name = esField.name();
        if (!StringUtils.isEmpty(name)) {
            return name;
        }

        String value = esField.value();
        if (!StringUtils.isEmpty(value)) {
            return value;
        }

        return esFieldName;
    }

    /**
     * 提取的代码，太长了
     *
     * @param field      EsQueryRange 类型的字段
     * @param rangeQuery 范围查询对象
     * @return RangeQueryBuilder
     */
    private static RangeQueryBuilder makeRangeQueryBuilder(Field field, EsQueryRange<?> rangeQuery) {
        EsFieldName annotation = field.getAnnotation(EsFieldName.class);
        if (annotation == null) {
            return null;
        }
        String propertyName = annotation.name();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(propertyName);
        Object upperValue = rangeQuery.getUpperValue();
        Object lowerValue = rangeQuery.getLowerValue();
        // ES 底层对日期类型只支持UTC类型时间，现在我们拿到的是GMT 类型的日期。
        // 不明白这两个时间之间为什么会有偏差。这里将时区的偏差加上以处理日期的范围查询
        if (upperValue instanceof Date) {
            upperValue = ((Date) upperValue).getTime() + TimeZone.getDefault().getRawOffset();
        }
        if (lowerValue instanceof Date) {
            lowerValue = ((Date) lowerValue).getTime() + TimeZone.getDefault().getRawOffset();
        }
        rangeQueryBuilder.from(lowerValue, rangeQuery.isLowerInclude());
        rangeQueryBuilder.to(upperValue, rangeQuery.isUpperInclude());
        return rangeQueryBuilder;
    }
}
