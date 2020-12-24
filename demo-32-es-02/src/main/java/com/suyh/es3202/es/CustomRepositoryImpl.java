package com.suyh.es3202.es;

import com.suyh.es3202.entity.EsQueryRange;
import com.suyh.es3202.repository.CustomRepository;
import com.suyh.init.CommonInit;
import com.suyh.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * 自定义
 *
 * @author 苏雲弘
 * @since 2020-12-04
 */
@Slf4j
public class CustomRepositoryImpl<T, ID> extends SimpleElasticsearchRepository<T, ID> implements CustomRepository<T, ID> {

    private RestHighLevelClient restHighLevelClient;
    public CustomRepositoryImpl(ElasticsearchEntityInformation<T, ID> metadata, ElasticsearchOperations elasticsearchOperations) {
        super(metadata, elasticsearchOperations);
    }

    /**
     * 获取RestHighLevelClient 对象
     *
     * @return RestHighLevelClient 实例
     */
    public RestHighLevelClient getClient() {
        if (restHighLevelClient == null) {
            restHighLevelClient =  CommonInit.getBean(RestHighLevelClient.class);
        }
        return restHighLevelClient;
    }

    public String indexName() {
        return entityInformation.getIndexName();
    }

    @Override
    public <E> Collection<T> search(E queryObj) {
        BoolQueryBuilder boolQueryBuilder = makeMatchQuery(queryObj);
        log.info("search(E queryObj), boolQueryBuilder: {}", boolQueryBuilder);
        Iterable<T> resultIter = search(boolQueryBuilder);
        List<T> resultList = new ArrayList<>();
        resultIter.forEach(resultList::add);
        return resultList;
    }

    public Page<T> search(T queryObj, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = makeMatchQuery(queryObj);
        log.info("search(E queryObj, Pageable pageable), boolQueryBuilder: {}", boolQueryBuilder);
        return search(boolQueryBuilder, pageable);
    }

    @Override
    public <E> Collection<T> searchLike(E queryObj) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolLikeQueryBuilder = makeLikeQuery(queryObj);
        nativeSearchQueryBuilder.withQuery(boolLikeQueryBuilder);
        log.info("searchLike(E queryObj), boolLikeQueryBuilder: {}", boolLikeQueryBuilder);
        Iterable<T> resultIter = search(nativeSearchQueryBuilder.build());
        List<T> resultList = new ArrayList<>();
        resultIter.forEach(resultList::add);
        return resultList;
    }

    @Override
    public <E> Page<T> searchLike(E queryObj, Pageable pageable) {
        BoolQueryBuilder boolLikeQueryBuilder = makeLikeQuery(queryObj);
        log.info("searchLike(E queryObj, Pageable pageable), boolLikeQueryBuilder: {}", boolLikeQueryBuilder);
        return search(boolLikeQueryBuilder, pageable);
    }

    @Override
    public void deleteByQuery(T queryObj) {
        BoolQueryBuilder boolQueryBuilder = makeMatchQuery(queryObj);

        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(boolQueryBuilder);
        deleteQuery.setType(entityInformation.getType());
        deleteQuery.setIndex(entityInformation.getIndexName());

        log.info("deleteByQuery(E queryObj), query: {}", boolQueryBuilder);
        elasticsearchOperations.delete(deleteQuery);
    }


    @Override
    public boolean recreateIndex() {
        log.info("recreateIndex");
        Class<T> clazz = entityInformation.getJavaType();
        elasticsearchOperations.deleteIndex(clazz);
        return elasticsearchOperations.createIndex(clazz) && elasticsearchOperations.putMapping(clazz);
    }

    @Override
    public <E> void updateByNeed(E updateObj, String id) {
        UpdateRequest updateRequest = new UpdateRequest();
        // 这里还有待优化的空间，可以使用EntityMapper bean对象的mapToString() 方法来进行json 序列化。
        String jsonObj = JsonUtil.serializable(updateObj);
        updateRequest.doc(jsonObj, XContentType.JSON);
        UpdateQuery updateQuery = new UpdateQuery();
        updateQuery.setId(id);
        updateQuery.setIndexName(entityInformation.getIndexName());
        updateQuery.setType(entityInformation.getType());
        updateQuery.setUpdateRequest(updateRequest);

        log.info("jsonObj: {}", jsonObj);
        log.info("updateByNeed(E queryObj, String id), updateRequest: {}", updateRequest);
        elasticsearchOperations.update(updateQuery);
    }

    @Override
    public <E> void updateByQuery(E queryObj, T updateObj) {
        String indexName = entityInformation.getIndexName();

        BoolQueryBuilder boolQueryBuilder = makeMatchQuery(queryObj);
        UpdateByQueryRequest request = new UpdateByQueryRequest(indexName);
        request.setConflicts("proceed");    // 失败继续
        request.setQuery(boolQueryBuilder); // 查询条件

        Script updateScript = makeUpdateScriptByEntity(updateObj);
        request.setScript(updateScript);

        try {
            log.info("boolQueryBuilder: {}", boolQueryBuilder);
            BulkByScrollResponse bulkResponse = getClient().updateByQuery(request, RequestOptions.DEFAULT);
            log.info("updateByQuery result, totalDocs: {}, updateDocs: {}", bulkResponse.getTotal(),
                    bulkResponse.getUpdated());
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }


    /**
     * 通过方法调用
     * 重建索引，同时添加一个自定义分词器
     *
     * PUT /tpl_wf_procform_t
     * {
     * ...."settings": {
     * ........"analysis": {
     * ............"analyzer": {
     * ................"my_group_id_analyzer": {
     * ...................."type": "pattern",
     * ...................."pattern": "[,\\s]",
     * ...................."lowercase": true
     * ................}
     * ............}
     * ........}
     * ....}
     * }
     *
     * @return
     */
    public boolean recreateIndexBySetting() {
        XContentBuilder builder = null;
        try {
            builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.startObject("analysis");
                {
                    builder.startObject("analyzer");
                    {
                        // 自定义分词器名
                        builder.startObject("my_group_id_analyzer");
                        {
                            // 分词器类型为：正则
                            builder.field("type", "pattern");
                            // 正则匹配，分词符号，见到这些符号则分词
                            builder.field("pattern", "[,\\s]");
                            // 这个应该是匹配的时候忽略大小写吧
                            builder.field("lowercase", true);
                        }
                        builder.endObject();
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Class<T> clazz = entityInformation.getJavaType();
        // 先删除已存在的索引
        elasticsearchOperations.deleteIndex(clazz);
        // 重建索引，同时按实体对象的注解进行更新mapping
        return elasticsearchOperations.createIndex(clazz, builder) && elasticsearchOperations.putMapping(clazz);
    }

    /**
     * 这里的效果与上面的(recreateIndexBySetting)一样，只是使用了不同的参数
     * @return
     */
    public boolean recreateIndexAnalyzer() {
        Settings pattern = Settings.builder()
                .put("analysis.analyzer.my_group_id_analyzer.type", "pattern")
                .put("analysis.analyzer.my_group_id_analyzer.lowercase", true)
                .put("analysis.analyzer.my_group_id_analyzer.pattern", "[, ]")
                .build();

        Class<T> clazz = entityInformation.getJavaType();
        elasticsearchOperations.deleteIndex(clazz);
        return elasticsearchOperations.createIndex(clazz, pattern);
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
     * 精确查询与分词查询
     *
     * @param entity            查询匹配条件，按类属性名称进行拼接
     * @param clazz             进行条件拼接的属性所属的类，仅处理该类通过递归处理直到全部处理完成。
     * @param matchQueryBuilder bool 查询构造器
     * @param <E>               泛型
     */
    public static <E> void makeMatchQuery(E entity, Class<?> clazz, BoolQueryBuilder matchQueryBuilder) {
        if (entity == null || clazz == null) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            makeMatchQuery(entity, field, matchQueryBuilder);
        }

        // 递归处理父类
        makeMatchQuery(entity, clazz.getSuperclass(), matchQueryBuilder);
    }

    /**
     * 对某个字段的处理，主要是做代码提取以及异常的处理，cleancode 时缩进太多
     * 同时简化外部调用对异常的单独处理
     *
     * @param entity            实体对象
     * @param field             字段
     * @param matchQueryBuilder 匹配器
     * @param <E>               实体泛型
     */
    private static <E> void makeMatchQuery(E entity, Field field, BoolQueryBuilder matchQueryBuilder) {
        if (Modifier.isStatic(field.getModifiers())) {
            return;
        }
        Object fieldValue = getFieldValue(entity, field);
        if (fieldValue == null) {
            return;
        }

        if (fieldValue instanceof EsQueryRange) {
            return;
        }

        // 日期不做等值匹配，就算要做等值匹配这里也是需要像范围查询那样做处理的。
        if (fieldValue instanceof Date) {
            return;
        }

        // 这里还有些不准确，如果数据类型是其它的对象类型的话这样是有问题的
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery(field.getName(), fieldValue);
        matchQueryBuilder.must(queryBuilder);
    }

    /**
     * 生成匹配查询
     *
     * @param entity 查询匹配条件，所有的条件以and 拼接
     * @param <E>    泛型
     * @return elastic search 提供的bool 查询器
     */
    public static <E> BoolQueryBuilder makeLikeQuery(E entity) {
        BoolQueryBuilder likeQueryBuilder = QueryBuilders.boolQuery();
        makeLikeQuery(entity, entity.getClass(), likeQueryBuilder);
        return likeQueryBuilder;
    }

    /**
     * 生成匹配查询
     * 精确查询与分词查询
     *
     * @param entity           查询匹配条件，按类属性名称进行拼接
     * @param clazz            进行条件拼接的属性所属的类，仅处理该类通过递归处理直到全部处理完成。
     * @param likeQueryBuilder bool 查询构造器
     * @param <E>              泛型
     */
    public static <E> void makeLikeQuery(E entity, Class<?> clazz, BoolQueryBuilder likeQueryBuilder) {
        if (entity == null || clazz == null) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            makeLikeQuery(entity, field, likeQueryBuilder);
        }

        // 递归处理父类
        makeLikeQuery(entity, clazz.getSuperclass(), likeQueryBuilder);
    }

    /**
     * 对某个字段的处理，主要是做代码提取以及异常的处理，cleancode 时缩进太多
     * 同时简化外部调用对异常的单独处理
     *
     * @param entity           实体对象
     * @param field            字段
     * @param likeQueryBuilder 匹配器
     * @param <E>              实体对象泛型
     */
    private static <E> void makeLikeQuery(E entity, Field field, BoolQueryBuilder likeQueryBuilder) {
        if (Modifier.isStatic(field.getModifiers())) {
            return;
        }
        Object fieldValue = getFieldValue(entity, field);
        if (fieldValue == null) {
            return;
        }

        // 日期不做等值匹配，就算要做等值匹配这里也是需要像范围查询那样做处理的。
        if (fieldValue instanceof Date) {
            return;
        }

        if (fieldValue instanceof EsQueryRange) {
            // 范围过滤
            EsQueryRange<?> rangeQuery = (EsQueryRange<?>) fieldValue;
            if (rangeQuery.isValid()) {
                likeQueryBuilder.must(makeRangeQueryBuilder(field, rangeQuery));
            }
        } else {
            likeQueryBuilder.must(makeFieldLikeQuery(field, fieldValue));
        }
    }

    /**
     * 创建字段的查询子句
     * 对于keyword 才有模糊查询 text 似乎只有分词查询与全限定查询。
     *
     * @param field      类字段属性
     * @param fieldValue 字段值
     * @return 查询子句
     */
    public static QueryBuilder makeFieldLikeQuery(Field field, Object fieldValue) {
        if (fieldValue instanceof String) {
            org.springframework.data.elasticsearch.annotations.Field esField = field.getAnnotation(
                    org.springframework.data.elasticsearch.annotations.Field.class);
            if (esField != null && esField.type().equals(FieldType.Keyword)) {
                // 对于keyword 类型，需要做模糊查询，其它类型直接做分词匹配查询
                return QueryBuilders.wildcardQuery(field.getName(), "*" + fieldValue + "*");
            }
            if (esField == null || esField.type().equals(FieldType.Auto)) {
                return QueryBuilders.wildcardQuery(field.getName() + ".keyword", "*" + fieldValue + "*");
            }
        }

        return QueryBuilders.matchQuery(field.getName(), fieldValue);
    }

    /**
     * 提取的代码，太长了
     * 处理范围查询的字段生成查询子句
     *
     * @param field      CelonEsQueryRange 类型的字段
     * @param rangeQuery 范围查询对象
     * @return RangeQueryBuilder
     */
    public static RangeQueryBuilder makeRangeQueryBuilder(Field field, EsQueryRange<?> rangeQuery) {
        EsFieldName annotation = field.getAnnotation(EsFieldName.class);
        if (annotation == null) {
            throw new RuntimeException("lose @CelonEsFieldName");
        }
        String propertyName = annotation.name();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(propertyName);
        Object lowerValue = rangeQuery.getLowerValue();
        Object upperValue = rangeQuery.getUpperValue();
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

    /**
     * 这部分代码仅做备份，如果上面的范围查询没有问题则不需要了，ES 的底层日期类型与当前系统中的日期类型处理已经一致处理了。
     * 提取的代码，太长了
     * 处理范围查询的字段生成查询子句
     *
     * @param field      CelonEsQueryRange 类型的字段
     * @param rangeQuery 范围查询对象
     * @return RangeQueryBuilder
     */
    public static RangeQueryBuilder makeRangeQueryBuilderBackup(Field field, EsQueryRange<?> rangeQuery) {
        EsFieldName annotation = field.getAnnotation(EsFieldName.class);
        if (annotation == null) {
            throw new RuntimeException("lose @CelonEsFieldName");
        }
        String propertyName = annotation.name();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(propertyName);
        Object upperValue = rangeQuery.getUpperValue();
        Object lowerValue = rangeQuery.getLowerValue();
        rangeQueryBuilder.from(lowerValue, rangeQuery.isLowerInclude());
        rangeQueryBuilder.to(upperValue, rangeQuery.isUpperInclude());
        return rangeQueryBuilder;
    }

    /**
     * 生成一个elasticSearch的更新脚本对象
     *
     * @param paramsObj 更新实体对象
     * @param <T>       泛型
     * @return 返回更新脚本
     */
    public static <T> Script makeUpdateScriptByEntity(T paramsObj) {
        StringBuilder sbCode = new StringBuilder();
        fillUpdateScriptCode(paramsObj.getClass(), sbCode);

        String jsonParams = JsonUtil.serializable(paramsObj);
        Map<String, Object> paramsMap = JsonUtil.deserializeToMap(jsonParams, String.class, Object.class);
        log.info("script code: {}", sbCode.toString());
        log.info("paramsMap: {}", paramsMap);
        return new Script(ScriptType.INLINE, "painless", sbCode.toString(), paramsMap);
    }

    /**
     * 按递归处理，实体对象的所有
     *
     * @param clazz  泛型类对象
     * @param sbCode StringBuilder
     * @param <T>    泛型
     */
    private static <T> void fillUpdateScriptCodeAndParams(Class<?> clazz, T obj, StringBuilder sbCode,
            Map<String, Object> params) {
        if (clazz == null) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            // 示例: if (params.{fieldName} != null) {
            // 示例:     ctx._source['{fieldName}'] = params.{fieldName};
            // 示例: }
            String fieldName = field.getName();
            sbCode.append("if (params.").append(fieldName).append(" != null) {");
            sbCode.append("ctx._source['").append(fieldName).append("'] = params.").append(fieldName).append(";");
            sbCode.append("}");

            Object fieldValue = getFieldValue(obj, field);
            if (fieldValue == null) {
                continue;
            }

            Object paramValue = fieldValue;
            if (fieldValue instanceof String) {
                // 空白字符串忽略
                if (StringUtils.isEmpty(fieldValue)) {
                    continue;
                }
            } else if (fieldValue instanceof Date) {
                String dateJson = JsonUtil.serializable(fieldValue);
                // 去除前尾的双引号，由json序列化之后，会在日期转换的结果字符串添加前后双引号，这里将其做删除处理。
                paramValue = dateJson.substring(1, dateJson.length() - 1);
            }
            params.put(fieldName, paramValue);
        }

        // 递归所有父类属性
        Class<?> superclass = clazz.getSuperclass();
        fillUpdateScriptCodeAndParams(superclass, obj, sbCode, params);
    }

    /**
     * 按递归处理，实体对象的所有
     *
     * @param clazz  泛型类对象
     * @param sbCode StringBuilder
     * @param <T>    泛型
     */
    private static <T> void fillUpdateScriptCode(Class<T> clazz, StringBuilder sbCode) {
        if (clazz == null) {
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            // 示例: if (params.{fieldName} != null) {
            // 示例:     ctx._source['{fieldName}'] = params.{fieldName};
            // 示例: }
            String fieldName = field.getName();
            sbCode.append("if (params.").append(fieldName).append(" != null) {");
            sbCode.append("ctx._source['").append(fieldName).append("'] = params.").append(fieldName).append(";");
            sbCode.append("}");
        }

        // 递归所有父类属性
        Class<? super T> superclass = clazz.getSuperclass();
        fillUpdateScriptCode(superclass, sbCode);
    }

    private static <T> Object getFieldValue(T entityObject, Field field) {
        try {
            field.setAccessible(true);
            return field.get(entityObject);
        } catch (IllegalAccessException exception) {
            log.error("CelonEsRepositoryImpl, getFieldValue failed.");
            throw new RuntimeException(exception.getMessage());
        }
    }

    /**
     * bool 查询的嵌套条件
     * {
     * .."bool": {
     * ...."should": [
     * ......{
     * ........"match": {
     * .........."userIds": "dGVzdDE="
     * ........}
     * ......},
     * ......{
     * ........"match": {
     * .........."groupIds": "MySQL_test_2.test"
     * ........}
     * ......}
     * ....]
     * ..}
     * }
     */
    private static void function(BoolQueryBuilder boolQueryBuilder) {
        // 创建一个嵌套的查询
        BoolQueryBuilder myTaskQueryBuilder = new BoolQueryBuilder();

        // 添加嵌套查询条件，这里使用逻辑或
        MatchQueryBuilder matchUserId = QueryBuilders.matchQuery("userIds", "userIdValue");
        myTaskQueryBuilder.should(matchUserId);

        // 再添加一个嵌套查询条件，这里也是逻辑或
        List<String> groupIdList = new ArrayList<>();
        if (groupIdList != null && !groupIdList.isEmpty()) {
            String groupIds = "groupId01,groupId02,groupId03";
            MatchQueryBuilder matchGroupId = QueryBuilders.matchQuery("groupIds", groupIds);
            myTaskQueryBuilder.should(matchGroupId);
        }

        boolQueryBuilder.must(myTaskQueryBuilder);
    }
}
