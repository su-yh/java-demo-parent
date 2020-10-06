package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooleanFormProperty extends AbstractFormProperty {
    public final static FormType FORM_TYPE = FormType.BOOLEAN;

    public BooleanFormProperty() {
        super(FORM_TYPE);
    }

    private boolean value = false;

    @Override
    public Object getValue() {
        return value;
    }
}
