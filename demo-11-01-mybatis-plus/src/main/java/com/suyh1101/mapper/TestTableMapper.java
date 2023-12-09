package com.suyh1101.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suyh1101.entity.TestTableEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author suyh
 * @since 2023-12-09
 */
@Mapper
public interface TestTableMapper extends BaseMapper<TestTableEntity> {
}
