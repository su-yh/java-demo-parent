package com.suyh5802.web.base.mvc.exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 异常分类
 *
 * @author suyh
 * @since 2023-11-26
 */
public enum ExceptionCategory {
    @Schema(description = "业务异常")
    BUSINESS,

    @Schema(description = "系统异常")
    SYSTEM,

    ;
}
