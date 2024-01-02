package com.suyh.mapper;

import com.suyh.entity.CrmCustomerInfo;
import com.suyh.entity.CrmCustomerInfoFilter;
import com.suyh.utils.CustomBaseMapper;
import com.suyh.utils.CustomerOracleMapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 使用到了自定义的那些通用实现，以及通用Mapper 提供的常用接口
 * 这里用到了自定义的Oracle 的实现，所以这里不能用其他数据库
 */
public interface CrmCustomerInfoMapper
        extends CustomBaseMapper<CrmCustomerInfo, CrmCustomerInfoFilter>,
        CustomerOracleMapper<CrmCustomerInfo, CrmCustomerInfoFilter>,
        Mapper<CrmCustomerInfo> {

    @Select("SELECT CREATED_BY FROM CRM_CUSTOMER_INFO")
    List<String> selectCreateBy();
}
