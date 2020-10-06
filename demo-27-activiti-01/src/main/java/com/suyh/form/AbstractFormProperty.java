package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class AbstractFormProperty implements FormProperty, Serializable {
    public static final Long serialVersionUID = 42L;

    public AbstractFormProperty(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    protected PropertyType propertyType;
    protected boolean required = false;
    /**
     * 变量名
     */
    protected String key;
    protected String description;

    @Override
    public Object getValue() {
        return null;
    }
}
