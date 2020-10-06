package com.suyh.form;

import com.suyh.form.entity.FormPropertyTemplateEntity;
import org.springframework.stereotype.Component;

/**
 * 表单数据处理工厂
 */
@Component
public class FormDataFactory {
    public AbstractFormProperty convertFormProperty(FormPropertyTemplateEntity entity) {
        // TODO:
        return null;
    }

    public FormPropertyTemplateEntity convertFormEntity(AbstractFormProperty formProperty) {
        // TODO:
        return null;
    }
}
