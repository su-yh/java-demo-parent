package com.suyh2201.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ParentEntity implements Serializable {
    public static final long serialVersionUID = 42345L;

    @NotNull
    private Integer id;

    @Valid  // 添加此注解，可以对该实体进行鞋套校验，进而校验此实体中添加校验注解的字段
    private ChildEntity childEntity;
}
