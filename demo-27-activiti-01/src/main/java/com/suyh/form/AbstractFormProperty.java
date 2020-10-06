package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class AbstractFormProperty implements FormProperty, Serializable {
    public static final Long serialVersionUID = 42L;

    public AbstractFormProperty(FormType formType) {
        this.formType = formType;
    }

    protected FormType formType;
    protected boolean required = false;
    protected String key;
    protected String description;
}
