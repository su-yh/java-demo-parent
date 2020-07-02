package com.suyh.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class Notice implements Serializable {
    private static final long serialVersionUID = 42L;

    private int status;
    private Object msg;
    private List<DataBean> data;
}
