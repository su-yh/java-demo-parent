package com.suyh.mapper;

import com.suyh.model.CrmCustomerInfo;
import com.suyh.model.CrmCustomerInfoFilter;
import java.util.List;

public interface CrmCustomerInfoMapper
        extends SimpleMapper<CrmCustomerInfo>,
        LikeMapper<CrmCustomerInfo, CrmCustomerInfoFilter> {
    int deleteByPrimaryKey(String customerId);

    int insert(CrmCustomerInfo record);

    CrmCustomerInfo selectByPrimaryKey(String customerId);

    List<CrmCustomerInfo> selectAll();
}