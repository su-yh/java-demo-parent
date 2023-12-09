package com.suyh1101.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author suyh
 * @since 2023-12-09
 */
@TableName(value = "test_uuid", autoResultMap = true)
@Data
public class TestUuidEntity {
    // 对于字符串的uuid，如果设置为: IdType.ASSIGN_UUID, 调用的是 IdentifierGenerator.nextUUID(..)
    // 如果没有设置类型，则会直接生成数字类型的ID，然后添加到数据为中。
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String nickName;

    private Integer age;
}
