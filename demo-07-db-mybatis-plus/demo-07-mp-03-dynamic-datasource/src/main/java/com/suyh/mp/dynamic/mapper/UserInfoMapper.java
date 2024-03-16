package com.suyh.mp.dynamic.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suyh.mp.dynamic.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author suyh
 * @since 2024-03-16
 */
@Mapper
@DS("dynamic-001")
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {

    // 报错，因为这个数据源中没有这张表，为了测试用的。
    @DS("dynamic-002")
    default List<UserInfoEntity> selectList() {
        LambdaQueryWrapper<UserInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        return selectList(queryWrapper);
    }
}
