package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 字符串类型的表单属性
 */
@Getter
@Setter
public class StringFormProperty extends AbstractFormProperty {
    public final static PropertyType PROPERTY_TYPE = PropertyType.STRING;

    public StringFormProperty() {
        super(PROPERTY_TYPE);
    }
}
