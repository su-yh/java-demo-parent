package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooleanFormProperty extends AbstractFormProperty {
    public final static PropertyType PROPERTY_TYPE = PropertyType.BOOLEAN;

    public BooleanFormProperty() {
        super(PROPERTY_TYPE);
    }
}
