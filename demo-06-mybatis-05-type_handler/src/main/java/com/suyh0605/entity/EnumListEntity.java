package com.suyh0605.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.suyh0605.enums.StatusEnum;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author suyh
 * @since 2023-12-02
 */
@TableName(value = "enum_list", autoResultMap = true)
@Data
public class EnumListEntity {
    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = JacksonTypeHandler.class)
    private List<StatusEnum> statusList;
}
