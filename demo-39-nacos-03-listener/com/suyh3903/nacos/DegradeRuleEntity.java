package com.suyh3903.nacos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DegradeRuleEntity implements Serializable {
    static final long serialVersionUID = 42L;

    private Long id;
    private String app;

    private String ip;
    private Integer port;

    private String resource;
    private String limitApp;
    private Double count;
    private Integer timeWindow;
    private Integer grade;
    private Integer minRequestAmount;
    private Double slowRatioThreshold;
    private Integer statIntervalMs;

    private Date gmtCreate;
    private Date gmtModified;
}
