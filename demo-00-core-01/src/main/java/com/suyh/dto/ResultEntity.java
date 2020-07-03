package com.suyh.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回实体基类
 */
@Data
@NoArgsConstructor
public abstract class ResultEntity  implements Serializable {
    private static final long serialVersionUID = 344142L;

    /**
     * 返回码
     */
    private int code;
    /**
     * 描述
     */
    private String desc;
}
