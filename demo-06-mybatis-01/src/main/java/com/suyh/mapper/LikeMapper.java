package com.suyh.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模糊查询 接口 mapper
 * 这个接口文件也需要提供出去
 *
 * @param <Model>  实体类
 * @param <Filter> 扩展实体类，对日期类型需要扩展Before字段和After 字段
 */
public interface LikeMapper<Model, Filter> {
    /**
     * 过滤(模糊)查询
     * @param filter
     * @return
     */
    List<Model> selectModelByFilterLike(@Param("filter") Filter filter);

}
