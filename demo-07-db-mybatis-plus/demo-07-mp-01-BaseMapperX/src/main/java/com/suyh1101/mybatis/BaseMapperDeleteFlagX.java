package org.jeecg.mp;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.entity.AbstractDeletedFlagEntity;

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
