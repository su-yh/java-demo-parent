package com.suyh.es3202.es;

import com.suyh.es3202.repository.CustomRepository;
import com.suyh.es3202.util.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;

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
    public void someCustomMethod() {
        log.info("CustomRepositoryImpl someCustomMethod ");
    }

    @Override
    public Page<T> search(T queryObj, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = EsUtils.makeMatchQuery(queryObj);
        return search(boolQueryBuilder, pageable);
    }

    @Override
    public void deleteByQuery(T queryObj) {
        BoolQueryBuilder boolQueryBuilder = EsUtils.makeMatchQuery(queryObj);

        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(boolQueryBuilder);
        deleteQuery.setType(entityInformation.getType());
        deleteQuery.setIndex(entityInformation.getIndexName());

        elasticsearchOperations.delete(deleteQuery);
    }
}
