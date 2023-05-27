package com.suyh2105.response;


import lombok.Data;

import java.util.Date;

@Data
public class SuyhResult<T> {
    private static Integer OK = 0;
    private static final String SUCCESS = "SUCCESS";

    private Integer code;
    private Boolean success;
    private String message;
    private Date timestamp;
    private T data;

    private SuyhResult(Integer code, String message, Date timestamp) {
        this.code = code;
        this.success = OK.equals(code);
        this.message = message;
        this.timestamp = timestamp;
    }

    private SuyhResult(Integer code, String message, Date timestamp, T data) {
        this.code = code;
        this.success = OK.equals(code);
        this.message = message;
        this.timestamp = timestamp;
        this.data = data;
    }

    public static <T> SuyhResult<T> ofSuccess(T data) {
        return new SuyhResult<>(OK, SUCCESS, new Date(), data);
    }

    public static <T> SuyhResult<T> ofSuccess() {
        return new SuyhResult<>(SuyhResult.OK, SUCCESS, new Date());
    }

    public static <T> SuyhResult<T> ofFail(Integer code, String msg, T data) {
        return new SuyhResult<>(code, msg, new Date(), data);
    }

    public static <T> SuyhResult<T> ofFail(String msg) {
        return new SuyhResult<>(-1, msg, new Date());
    }

    public static <T> SuyhResult<T> ofResultCode(ResultCode code) {
        return new SuyhResult<>(code.getCode(), code.getDesc(), new Date());
    }

    public static <T> SuyhResult<T> ofResultCode(ResultCode code, T data) {
        return new SuyhResult<>(code.getCode(), code.getDesc(), new Date(), data);
    }
}

