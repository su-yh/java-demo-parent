package com.suyh.utils;

import com.suyh.utils.impl.CustomerOracleMapperProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * 自定义过滤查询，但仅支持Oracle。
 *
 * @param <Model>
 * @param <Filter>
 */
@RegisterMapper
public interface CustomerOracleMapper<Model, Filter> {
    /**
     * 过滤(模糊)查询
     * @param filter
     * @return
     */
    @SelectProvider(type = CustomerOracleMapperProvider.class, method = "dynamicSQL")
    List<Model> selectModelByFilterLike(@Param("filter") Filter filter);

    /**
     * 过滤(匹配+模糊)查询
     * 可以指定部分字段匹配 + 部分字段模糊查询
     * @param filterMatch 指定匹配查询的字段
     * @param filterLike 指定模糊查询的字段
     * @return
     */
    @SelectProvider(type = CustomerOracleMapperProvider.class, method = "dynamicSQL")
    List<Model> selectModelByFilterMatchLike(
            @Param("filterMatch") Filter filterMatch,
            @Param("filterLike") Filter filterLike);
}
