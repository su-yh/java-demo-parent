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

    public ResultPage(PageInfoDto pageInfo, List<T> data) {
        this.pageInfo = pageInfo;
        this.data = data;
    }

    private PageInfoDto pageInfo;
    private List<T> data;
}
