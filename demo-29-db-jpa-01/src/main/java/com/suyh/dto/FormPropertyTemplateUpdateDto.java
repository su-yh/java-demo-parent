package com.suyh.dto;

import javax.validation.constraints.NotNull;

public class FormPropertyTemplateUpdateDto extends FormPropertyTemplateMiddleDto {

    /**
     * 更新实体对象时，ID 值必须传入，通过ID 进行查询校验
     *
     * @return
     */
    @NotNull
    @Override
    public Long getId() {
        return super.getId();
    }
}
