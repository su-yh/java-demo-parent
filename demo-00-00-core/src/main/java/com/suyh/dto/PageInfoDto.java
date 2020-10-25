package com.suyh.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfoDto implements Serializable {
    static final long serialVersionUID = 1L;

    /**
     * 当前页 以1开始为第一页
     */
    private int curPage = 1;

    /**
     * 每页长
     */
    private int pageSize = 10;

    private long totalRows = 0;
}
