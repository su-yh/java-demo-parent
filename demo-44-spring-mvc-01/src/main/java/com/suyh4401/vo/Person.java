package com.suyh4401.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 2899950741904558796L;

    private String name;
    private Integer age;

    private Student stu;
}
