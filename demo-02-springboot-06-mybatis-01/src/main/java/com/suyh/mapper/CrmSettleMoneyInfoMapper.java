package com.suyh.mapper;

import com.suyh.model.CrmSettleMoneyInfo;
import com.suyh.model.CrmSettleMoneyInfoFilter;
import java.util.List;

public interface CrmSettleMoneyInfoMapper extends SimpleMapper<CrmSettleMoneyInfo>, LikeMapper<CrmSettleMoneyInfo, CrmSettleMoneyInfoFilter> {
    int deleteByPrimaryKey(String customerId);

    int insert(CrmSettleMoneyInfo record);

    CrmSettleMoneyInfo selectByPrimaryKey(String customerId);

    List<CrmSettleMoneyInfo> selectAll();
}