package com.suyh.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PageInfoDto implements Serializable {
    static final long serialVersionUID = 1L;

    public PageInfoDto(int curPage, int pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;
    }
    
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
