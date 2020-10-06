package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class DateFormProperty extends AbstractFormProperty {
    public final static FormType FORM_TYPE = FormType.DATE;

    public DateFormProperty() {
        super(FORM_TYPE);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private SimpleDateFormat sdf;
    private Date value;

    @Override
    public Object getValue() {
        return value;
    }
}
