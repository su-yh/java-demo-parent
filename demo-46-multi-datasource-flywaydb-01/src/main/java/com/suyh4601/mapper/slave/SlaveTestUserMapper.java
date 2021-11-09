package com.suyh4601.mapper.slave;

import com.suyh4601.entity.slave.SlaveTestUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SlaveTestUserMapper {
    SlaveTestUser selectById(@Param("id") Long id);

    List<SlaveTestUser> selectAll();
}
