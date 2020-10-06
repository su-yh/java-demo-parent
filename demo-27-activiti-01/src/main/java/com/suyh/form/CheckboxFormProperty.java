package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CheckboxFormProperty extends AbstractFormProperty {
    public final static FormType FORM_TYPE = FormType.CHECKBOX;

    public CheckboxFormProperty() {
        super(FORM_TYPE);
    }

    // 可选值
    private List<String> optionals;

    @Override
    public Object getValue() {
        return optionals;
    }
}
