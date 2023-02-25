package com.suyh2103.vo;

public enum ErrorCode {
    OK(0, "成功"),
    ERROR(-1, "500 错误"),
    ERROR_SUYH(-2, "suyh 业务出错了"),
    ERROR_SYSTEM(-3, "服务错误");
    ;

    private final Integer code;
    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.desc = msg;
    }

}

