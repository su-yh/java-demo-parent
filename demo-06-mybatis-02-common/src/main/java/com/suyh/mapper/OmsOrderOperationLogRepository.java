package com.suyh.mapper;

import com.suyh.entity.OmsOrderOperationLog;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 这里是使用了通用Mapper 提供的一些常用实现，没有用到自定义的那些接口实现
 *
 * @author 苏雲弘
 * @Description:
 * @date 2020-05-07 11:32
 */
public interface OmsOrderOperationLogRepository
        extends Mapper<OmsOrderOperationLog> {

    @Select("select * from OMS_ORDER_OPERATION_LOG")
    List<OmsOrderOperationLog> selectAllSuyh();
}
