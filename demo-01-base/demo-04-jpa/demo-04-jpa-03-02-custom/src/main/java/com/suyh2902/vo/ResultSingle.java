package com.suyh2902.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 返回实体类
 *
 * @param <T>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResultSingle<T> extends ResultEntity {

    public ResultSingle(T data) {
        this.data = data;
    }

    private T data;
}
