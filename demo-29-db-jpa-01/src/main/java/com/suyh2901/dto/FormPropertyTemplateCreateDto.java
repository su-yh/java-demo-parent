package com.suyh2901.dto;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Null;

@MappedSuperclass
public class FormPropertyTemplateCreateDto extends FormPropertyTemplateMiddleDto {

    /**
     * 创建属性时，不允许传入ID 值。这个ID值因为是数据库自增的，所以不允许前端传入
     *
     * @return
     */
    @Null
    @Override
    public Long getId() {
        return super.getId();
    }
}
