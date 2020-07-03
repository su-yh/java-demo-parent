package com.suyh.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回实体类
 * @param <T>
 */
@Data
@NoArgsConstructor
public class ResultMode<T> extends ResultEntity {
    private static final long serialVersionUID = 344142L;

    private T data;
}
