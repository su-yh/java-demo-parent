package com.suyh2901.dto;

import com.suyh2901.entity.FormPropertyTemplateEntity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


/**
 * 这个类作为实体的中间类，分开创建实体与更新实体的校验不同。
 * 比如创建实体时，ID值必须为NULL
 * 而更新实体时，ID值必须非NULL
 * 但是不管是创建还是更新实体，对于其他字段(比如：varKey)。
 * 当它的值非空是都是相同的校验。
 */
//@MappedSuperclass // 这个注解的使用是在实体类的基类上面，而非子类
public class FormPropertyTemplateMiddleDto extends FormPropertyTemplateEntity {

    /**
     * 当ID 值非NULL 时，他的最小值为1，不允许为负数
     * @return
     */
    @Min(1)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Size(max = 255, message = "varKey 最大255 个字符")
    @Override
    public String getVarKey() {
        return super.getVarKey();
    }
}
