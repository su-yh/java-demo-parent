package com.suyh0310.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -5199378732329464693L;

    private String id;
    private String name;
    private int grade;
}
