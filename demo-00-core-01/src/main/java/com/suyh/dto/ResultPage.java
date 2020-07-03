package com.suyh.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 返回分页数据
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class ResultPage<T> extends ResultEntity {

    /**
     * 总记录数
     */
    private int total;
    /**
     * 当前页
     */
    private int page;
    private List<T> data;
}
