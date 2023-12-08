package com.suyh5802.web.base.mvc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 它们的值总是200 与OK，因为http 的响应码首先需要判断 status 的值
 *
 * @author suyh
 * @since 2023-11-26
 */
@Getter
public abstract class BaseResult {
    @Schema(description = "http 的正常状态：200、400、404、500 等")
    private final Integer status = HttpStatus.OK.value();

    @Schema(description = "http 展示的消息")
    private final String message = HttpStatus.OK.getReasonPhrase();
}
