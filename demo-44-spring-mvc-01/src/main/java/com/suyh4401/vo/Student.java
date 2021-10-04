package com.suyh4401.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    static final long serialVersionUID = 42L;

    private String number;
    private Long classNumber;
}
