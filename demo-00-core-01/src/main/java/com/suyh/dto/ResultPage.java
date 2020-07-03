package com.suyh.dto;

import lombok.*;

import java.util.List;

/**
 * 返回分页数据
 *
 * @param <T>
 */
@Getter
@Setter
@ToString
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
