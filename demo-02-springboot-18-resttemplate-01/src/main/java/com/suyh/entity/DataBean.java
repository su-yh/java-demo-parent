package com.suyh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class DataBean implements Serializable {
    private static final long serialVersionUID = 42L;

    private int noticeId;
    private String noticeTitle;
    private Object noticeImg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeUpdateTime;
    private String noticeContent;
}
