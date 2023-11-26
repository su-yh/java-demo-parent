package com.mastermile.proxy.mvc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author suyh
 * @since 2023-11-26
 */
@Data
public abstract class BaseResult {
    protected BaseResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @Schema(description = "http 的正常状态：200、400、404、500 等")
    private Integer status;

    @Schema(description = "http 展示的消息")
    private String message;
}
