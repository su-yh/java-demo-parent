package com.suyh.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 一个基本的 mapper 实现。
 * 就是添加过滤查询和过滤更新
 */
public interface SimpleMapper<T> {
    /**
     * 过滤查询，完全匹配
     * @param filter
     * @return
     */
    List<T> selectModelByFilter(@Param("filter") T filter);

    /**
     * 更新记录，指定过滤条件
     * @param model
     * @param filter
     * @return
     */
    int updateModelByFilter(@Param("model") T model, @Param("filter") T filter);
}

