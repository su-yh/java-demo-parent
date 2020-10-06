package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentFormProperty extends AbstractFormProperty {
    public final static FormType FORM_TYPE = FormType.ATTACHMENT;

    public AttachmentFormProperty() {
        super(FORM_TYPE);
    }

    protected String filePath;

    @Override
    public Object getValue() {
        return getFilePath();
    }
}
