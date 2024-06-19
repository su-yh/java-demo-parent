package com.suyh1101.mybatis;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.suyh1101.entity.AbstractDeletedFlagEntity;
import com.suyh1101.constant.CommonConstant;

/**
 * @author suyh
 * @since 2024-06-19
 */
public interface BaseMapperDeleteFlagX<T extends AbstractDeletedFlagEntity> extends BaseMapperX<T> {

    // 补充上删除标记判断
    default void deleteFlagWrapper(LambdaQueryWrapperX<T> queryWrapperX, boolean deleted) {
        SFunction<T, ?> sfDeleteFlag = sfDeleteFlag();
        if (deleted) {
            queryWrapperX.ne(sfDeleteFlag, CommonConstant.FLAG_EXISTS);
        } else {
            queryWrapperX.eq(sfDeleteFlag, CommonConstant.FLAG_EXISTS);
        }
    }

    SFunction<T, ?> sfDeleteFlag();
}
