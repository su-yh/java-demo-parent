package com.suyh1101.mapper;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.suyh1101.entity.TestLongIdEntity;
import com.suyh1101.mybatis.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;
import com.suyh1101.mybatis.BaseMapperDeleteFlagX;
import com.suyh1101.mybatis.LambdaQueryWrapperX;

/**
 * @author suyh
 * @since 2023-12-09
 */
@Mapper
public interface TestLongIdMapper extends BaseMapperDeleteFlagX<TestLongIdEntity> {
    default SFunction<TestLongIdEntity, ?> sfDeleteFlag() {
        return TestLongIdEntity::getDeleteFlag;
    }

    default int updateById(TestLongIdEntity entity, boolean deleted) {
        LambdaQueryWrapperX<TestLongIdEntity> queryWrapperX = build();
        queryWrapperX.eq(TestLongIdEntity::getId, entity.getId());
        deleteFlagWrapper(queryWrapperX, deleted);

        return update(entity, queryWrapperX);
    }
}
