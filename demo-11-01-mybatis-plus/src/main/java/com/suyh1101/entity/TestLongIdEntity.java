package com.suyh1101.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author suyh
 * @since 2023-12-09
 */
@TableName(value = "test_long_id", autoResultMap = true)
@Data
public class TestLongIdEntity {
    @TableId
    private Long id;

    private String nickName;

    private Integer age;
}
