package com.suyh.utils;

import com.suyh.utils.impl.CustomBaseMapperProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface CustomBaseMapper<Model, Filter>  {

    /**
     * 按指定过滤条件查询
     *
     * @param filter 过滤条件
     * @return
     */
    @SelectProvider(type = CustomBaseMapperProvider.class, method = "dynamicSQL")
    List<Model> selectModelByFilter(@Param("filter") Filter filter);

    /**
     * 更新记录，指定过滤条件
     * @param model 更新实体
     * @param filter 更新过滤条件
     * @return
     */
    @UpdateProvider(type = CustomBaseMapperProvider.class, method = "dynamicSQL")
    int updateModelByFilter(@Param("model") Model model, @Param("filter") Filter filter);


}
