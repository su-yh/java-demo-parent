package com.suyh0605.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author suyh
 * @since 2023-12-03
 */
@TableName("sql_date_test")
@Data
public class SqlDateTestEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用来测试java.sql.Date 是否受时区的影响。
     * 存到数据库中的是UTC 时间 戳还是 yyyy-MM-dd 的值
     */
    private java.sql.Date testSqlDate;

}
