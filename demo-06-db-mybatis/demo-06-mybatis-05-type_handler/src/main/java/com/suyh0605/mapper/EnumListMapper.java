package com.suyh0605.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suyh0605.entity.EnumListEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author suyh
 * @since 2023-12-02
 */
@Mapper
public interface EnumListMapper extends BaseMapper<EnumListEntity> {
    default List<EnumListEntity> selectList() {
        LambdaQueryWrapper<EnumListEntity> queryWrapper = new LambdaQueryWrapper<>();
        return selectList(queryWrapper);
    }
}
