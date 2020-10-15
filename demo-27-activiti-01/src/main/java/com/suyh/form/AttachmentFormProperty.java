package com.suyh.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentFormProperty extends AbstractFormProperty {
    public final static PropertyType PROPERTY_TYPE = PropertyType.ATTACHMENT;

    public AttachmentFormProperty() {
        super(PROPERTY_TYPE);
    }
}
