package com.suyh.mapper;

import com.suyh.model.CrmContactsInfo;
import com.suyh.model.CrmContactsInfoFilter;
import java.util.List;

public interface CrmContactsInfoMapper extends SimpleMapper<CrmContactsInfo>, LikeMapper<CrmContactsInfo, CrmContactsInfoFilter> {
    int deleteByPrimaryKey(String contactsId);

    int insert(CrmContactsInfo record);

    CrmContactsInfo selectByPrimaryKey(String contactsId);

    List<CrmContactsInfo> selectAll();
}