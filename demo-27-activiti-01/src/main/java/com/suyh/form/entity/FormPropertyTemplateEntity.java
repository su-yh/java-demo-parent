package com.suyh.form.entity;

import com.suyh.form.AbstractFormProperty;
import com.suyh.form.PropertyType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表单属性模板实体
 */
@Getter
@Setter
public class FormPropertyTemplateEntity extends AbstractFormProperty {
    public FormPropertyTemplateEntity() {
        super(PropertyType.UNKNOWN);
    }

    // 主键
    private Long id;
    // 业务key
    private String businessKey;

    // 日期解析格式
    private String pattern;
    // 日期类型数据值
    private String valueRadio;
    private String valueCheckbox;

    @Override
    public Object getValue() {
        return null;
    }
}
