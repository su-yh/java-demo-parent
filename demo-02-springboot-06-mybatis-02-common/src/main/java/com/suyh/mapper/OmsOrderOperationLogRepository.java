package com.suyh.mapper;

import com.suyh.entity.OmsOrderOperationLog;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-05-07 11:32
 */
// TODO: 这里是不是必须要Mapper
// TODO: 还是说必须要使用TK的MapperScan
public interface OmsOrderOperationLogRepository
        extends Mapper<OmsOrderOperationLog> {

    @Select("select * from OMS_ORDER_OPERATION_LOG")
    List<OmsOrderOperationLog> selectAllSuyh();
}
