package com.suyh5802.web.base.constant;


import lombok.Getter;

@Getter
public class ErrorCode {
    public static Integer SUCCESS_CODE = 0;
    public static final String SUCCESS_MSG = "SUCCESS";

    public static final ErrorCode SUCCESS = new ErrorCode(SUCCESS_CODE, SUCCESS_MSG);

    private final Integer code;

    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
