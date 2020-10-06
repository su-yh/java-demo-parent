package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
public class DateFormProperty extends AbstractFormProperty {
    public final static PropertyType PROPERTY_TYPE = PropertyType.DATE;

    public DateFormProperty() {
        super(PROPERTY_TYPE);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private SimpleDateFormat sdf;
    private Date value;

    @Override
    public Object getValue() {
        return value;
    }
}
