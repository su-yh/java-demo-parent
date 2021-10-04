package com.suyh4401.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 6297874087558744229L;

    private String number;
    private Long classNumber;
}
