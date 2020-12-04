package com.suyh.es3202.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author 苏雲弘
 * @since 2020-12-04
 */
@NoRepositoryBean
public interface CustomRepository<T, ID> extends ElasticsearchRepository<T, ID> {
    void someCustomMethod();

    /**
     * 按条件查询
     */
    Page<T> search(T queryObj, Pageable pageable);

    /**
     * 按条件删除
     */
    void deleteByQuery(T queryObj);
}
