package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CheckboxFormProperty extends AbstractFormProperty {
    public final static PropertyType PROPERTY_TYPE = PropertyType.CHECKBOX;

    public CheckboxFormProperty() {
        super(PROPERTY_TYPE);
    }

    // 可选值
    private List<String> optionals;

    @Override
    public Object getValue() {
        return optionals;
    }
}
