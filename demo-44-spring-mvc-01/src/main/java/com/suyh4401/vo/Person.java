package com.suyh4401.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    static final long serialVersionUID = 42L;

    private String name;
    private Integer age;

    private Student stu;
}
