package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Double 类型的表单属性
 */
@Getter
@Setter
public class DoubleFormProperty extends AbstractFormProperty {
    public final static PropertyType PROPERTY_TYPE = PropertyType.DOUBLE;

    public DoubleFormProperty() {
        super(PROPERTY_TYPE);
    }
}
