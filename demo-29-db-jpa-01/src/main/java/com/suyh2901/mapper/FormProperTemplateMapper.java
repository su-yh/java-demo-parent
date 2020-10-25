package com.suyh2901.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface FormProperTemplateMapper extends JpaRepository<com.suyh2901.entity.FormPropertyTemplateEntity, Long> {

    /**
     * select * from t where id in (id1, id2, ...)
     *
     * @param ids
     * @return
     */
    List<com.suyh2901.entity.FormPropertyTemplateEntity> findByIdIn(Collection<Long> ids);

    /**
     * 查询parentId 字段为NULL 的记录
     *
     * @return 结果集
     */
    List<com.suyh2901.entity.FormPropertyTemplateEntity> findByParentIdIsNull();

    /**
     * 这个解决了，某个字段为NULL 的情况的分页查询
     *
     * @param pageable 分页条件
     * @return 结果集
     */
    Page<com.suyh2901.entity.FormPropertyTemplateEntity> findByParentIdIsNull(Pageable pageable);








    /**
     * 问题：这个查询出来的是全部数据，并不是分页指定的条数
     * `@Query(value = "SELECT model FROM FormPropertyTemplateEntity model WHERE parentId IS NULL ORDER BY ?#{#pageable}")`
     * 你大爷的，这后面的 "ORDER BY ?#{#pageable}" 不能加，加了就是全部查询出来了，起不到分页的作用。
     * <p>
     * 自定义分页查询
     * 查询某个字段为NULL的情况
     * 对于某个字段为NULL的情况的，不好处理。    -- 已经处理了， findByParentIdIsNull(pageable)
     *
     * @param pageable 分页条件，页数从0 开始
     * @return 结果集
     */
    @Query(value = "SELECT model FROM FormPropertyTemplateEntity model WHERE parentId IS NULL")
    Page<com.suyh2901.entity.FormPropertyTemplateEntity> pageQueryParentIsNull(Pageable pageable);
}
