package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RadioFormProperty extends AbstractFormProperty {
    public final static PropertyType PROPERTY_TYPE = PropertyType.RADIO;

    public RadioFormProperty() {
        super(PROPERTY_TYPE);
    }

    // 可选值
    private List<String> optionals;

    @Override
    public Object getValue() {
        return optionals;
    }
}
