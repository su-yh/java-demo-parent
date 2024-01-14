package com.suyh5802.web.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suyh5802.web.base.entity.UserLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author suyh
 * @since 2023-12-27
 */
@Mapper
public interface UserLoginMapper extends BaseMapper<UserLoginEntity> {
}
