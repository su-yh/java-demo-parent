package com.suyh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * 表单属性模板实体
 */
@Data
@NoArgsConstructor
@TableName(value = "form_property_template", autoResultMap = true)
public class FormPropertyTemplateEntity implements Serializable {
    public static final long serialVersionUID = 42L;

    // 主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 业务key
    @TableField(value = "business_key", jdbcType = JdbcType.VARCHAR)
    private String businessKey;

    @TableField(value = "property_type", jdbcType = JdbcType.VARCHAR)
    private String propertyType;

    /**
     * 'Y' OR 'N'
     */
    @TableField(value = "required", jdbcType = JdbcType.CHAR)
    private String required;

    @TableField(value = "var_key", jdbcType = JdbcType.VARCHAR)
    private String varKey;

    @TableField(value = "description", jdbcType = JdbcType.VARCHAR)
    private String description;

    // 日期解析格式
    @TableField(value = "pattern", jdbcType = JdbcType.VARCHAR)
    private String pattern;

    @TableField(value = "value_custom", jdbcType = JdbcType.VARCHAR)
    private String valueCustom;

    @TableField(value = "value_custom_text", jdbcType = JdbcType.LONGVARCHAR)
    private String valueCustomText;
}
