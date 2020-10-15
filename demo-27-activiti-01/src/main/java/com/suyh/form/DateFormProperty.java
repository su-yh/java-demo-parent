package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DateFormProperty extends AbstractFormProperty {
    public final static PropertyType PROPERTY_TYPE = PropertyType.DATE;

    public DateFormProperty() {
        super(PROPERTY_TYPE);
    }

    private String pattern = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Object getValue() {
        return pattern;
    }
}
