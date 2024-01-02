package com.suyh0605.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suyh0605.enums.StatusEnum;
import com.suyh0605.typehandler.StatusListEnumTypeHandler;
import com.suyh0605.typehandler.SuyhBooleanTypeHandler;
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
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = StatusListEnumTypeHandler.class)
    private List<StatusEnum> statusList;

    // 要想存到数据库中的字符串是true/false 则jdbcType 必须为VARCHAR。
    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = SuyhBooleanTypeHandler.class)
    private Boolean flag;
}
