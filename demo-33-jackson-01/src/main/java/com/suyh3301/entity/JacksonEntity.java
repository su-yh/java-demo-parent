package com.suyh3301.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("JacksonEntity")
public class JacksonEntity implements Serializable {
    private String name;
    private Integer age;
    private Date birth;
    private Long nullVar;
}
