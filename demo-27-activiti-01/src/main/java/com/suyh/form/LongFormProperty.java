package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

/**
 * Long 类型的表单属性
 */
@Getter
@Setter
public class LongFormProperty extends AbstractFormProperty {
    public final static FormType FORM_TYPE = FormType.LONG;

    public LongFormProperty() {
        super(FORM_TYPE);
    }

    private Long value;

    @Override
    public Object getValue() {
        return value;
    }
}
