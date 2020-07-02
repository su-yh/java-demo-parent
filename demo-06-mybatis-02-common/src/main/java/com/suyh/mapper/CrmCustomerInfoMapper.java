package com.suyh.mapper;

import com.suyh.entity.CrmCustomerInfo;
import com.suyh.entity.CrmCustomerInfoFilter;
import com.suyh.utils.CustomBaseMapper;
import com.suyh.utils.CustomerOracleMapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CrmCustomerInfoMapper
        extends CustomBaseMapper<CrmCustomerInfo, CrmCustomerInfoFilter>,
        CustomerOracleMapper<CrmCustomerInfo, CrmCustomerInfoFilter>,
        Mapper<CrmCustomerInfo> {

    @Select("SELECT CREATED_BY FROM CRM_CUSTOMER_INFO")
    List<String> selectCreateBy();
}
