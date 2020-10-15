package com.suyh.mapper;

import com.suyh.entity.FormPropertyTemplateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormProperTemplateMapper extends JpaRepository<FormPropertyTemplateEntity, Long> {
    /**
     * TODO: 问题，这个查询出来的是全部数据，并不是分页指定的条数
     *
     * 自定义分页查询
     * 查询某个字段为NULL的情况
     * 对于某个字段为NULL的情况的，不好处理。
     *
     * @param pageable 分页条件，页数从0 开始
     * @return 返回列表
     */
    @Query(value = "SELECT model FROM FormPropertyTemplateEntity model WHERE parentId IS NULL ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(1) FROM FormPropertyTemplateEntity model WHERE parentId IS NULL")
    Page<FormPropertyTemplateEntity> pageQueryParentIsNull(Pageable pageable);
}
