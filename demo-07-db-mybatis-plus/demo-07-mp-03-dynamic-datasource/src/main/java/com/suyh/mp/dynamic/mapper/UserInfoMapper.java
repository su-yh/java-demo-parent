package com.suyh.mp.dynamic.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suyh.mp.dynamic.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author suyh
 * @since 2024-03-16
 */
@Mapper
@DS("dynamic-001")
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {
}
