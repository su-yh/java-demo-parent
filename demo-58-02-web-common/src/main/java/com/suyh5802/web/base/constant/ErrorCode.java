package com.suyh5802.web.base.constant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ErrorCode {
    public static Integer SUCCESS_CODE = 0;
    public static final String SUCCESS_MSG = "SUCCESS";

    public static final ErrorCode SUCCESS = new ErrorCode(SUCCESS_CODE, SUCCESS_MSG);

    @Schema(description = "错误码")
    private final Integer code;

    @Schema(description = "错误描述")
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
