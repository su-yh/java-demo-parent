package com.suyh.es3202.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

/**
 * @author 苏雲弘
 * @since 2020-12-04
 */
@NoRepositoryBean
public interface CustomRepository<T, ID> extends ElasticsearchRepository<T, ID> {
    <E> Collection<T> search(E queryObj);

    /**
     * 按条件查询
     */
    Page<T> search(T queryObj, Pageable pageable);

    /**
     * 按条件删除
     */
    void deleteByQuery(T queryObj);

    <E> Collection<T> searchLike(E queryObj);

    <E> Page<T> searchLike(E queryObj, Pageable pageable);

    boolean recreateIndex();

    <E> void updateByNeed(E updateObj, String id);

    <E> void updateByQuery(E queryObj, T updateObj);
}
