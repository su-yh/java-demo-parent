package com.suyh.dto;

import lombok.*;

/**
 * 返回实体类
 * @param <T>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResultMode<T> extends ResultEntity {
    private static final long serialVersionUID = 344142L;

    private T data;
}
