package com.suyh2103.vo;

public enum ErrorCode {
    OK(0, "成功"),
    ERROR(-1, "出错了"),
    ERROR_SUYH(-2, "suyh 的错误码枚举"),
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

