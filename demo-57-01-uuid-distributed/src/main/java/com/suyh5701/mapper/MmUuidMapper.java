package com.suyh5701.mapper;

import com.suyh5701.vo.MmUuidVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MmUuidMapper {
    MmUuidVo selectByPrimaryKey(@Param("serviceName") String serviceName);

    int updateByPrimaryKey(@Param("serviceName") String serviceName, @Param("sequenceNumber") Integer sequenceNumber);
}
