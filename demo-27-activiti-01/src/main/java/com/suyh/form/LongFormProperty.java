package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

/**
 * Long 类型的表单属性
 */
@Getter
@Setter
public class LongFormProperty extends AbstractFormProperty {
    public final static PropertyType PROPERTY_TYPE = PropertyType.LONG;

    public LongFormProperty() {
        super(PROPERTY_TYPE);
    }
}
