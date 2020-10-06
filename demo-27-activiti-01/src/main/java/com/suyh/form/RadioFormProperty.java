package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RadioFormProperty extends AbstractFormProperty {
    public final static FormType FORM_TYPE = FormType.RADIO;

    public RadioFormProperty() {
        super(FORM_TYPE);
    }

    // 可选值
    private List<String> optionals;

    @Override
    public Object getValue() {
        return optionals;
    }
}
