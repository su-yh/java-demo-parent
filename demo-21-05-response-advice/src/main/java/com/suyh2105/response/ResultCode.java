package com.suyh2105.response;

public enum ResultCode {
    OK(0, "操作成功"),

    ;

    private final Integer code;
    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.desc = msg;
    }

}

