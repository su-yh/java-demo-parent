package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 字符串类型的表单属性
 */
@Getter
@Setter
public class StringFormProperty extends AbstractFormProperty {
    public final static FormType FORM_TYPE = FormType.STRING;

    public StringFormProperty() {
        super(FORM_TYPE);
    }

    private String value;

    @Override
    public Object getValue() {
        return value;
    }
}
