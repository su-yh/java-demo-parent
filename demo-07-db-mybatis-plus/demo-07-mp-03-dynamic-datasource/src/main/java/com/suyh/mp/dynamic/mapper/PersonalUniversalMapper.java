package com.suyh.mp.dynamic.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suyh.mp.dynamic.constant.DynamicConstants;
import com.suyh.mp.dynamic.entity.PersonalUniversalEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author suyh
 * @since 2024-03-16
 */
@Mapper
@DS(DynamicConstants.DS002)
public interface PersonalUniversalMapper extends BaseMapper<PersonalUniversalEntity> {
}
