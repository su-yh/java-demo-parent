package com.suyh.es3202.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * 对es 的范围查询
 *
 * @author 苏雲弘
 * @since 2020-11-25
 */
@Data
@NoArgsConstructor
@ApiModel(value = "EsQueryRange", description = "对es 的范围查询")
@Slf4j
public class EsQueryRange<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 当前范围查询对象是否为有效查询对象
     *
     * @return true/false
     */
    @ApiModelProperty(hidden = true)
    public boolean isValid() {
        return (lowerValue != null) || (upperValue != null);
    }

    private T lowerValue;

    private T upperValue;

    private boolean lowerInclude = false;

    private boolean upperInclude = false;
}
