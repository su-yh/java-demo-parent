package com.suyh2103.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SuyhResult<T> {
    private static Integer OK = 0;
    private static final String SUCCESS = "SUCCESS";

    private Integer code;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date timestamp;
    private T data;

    private SuyhResult(Integer code, String message, T data, Date timestamp) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private SuyhResult(Integer code, String msg, Date timestamp) {
        this.code = code;
        this.message = msg;
        this.timestamp = timestamp;
    }

    public static <T> SuyhResult<T> ofSuccess(T data) {
        return new SuyhResult<>(0, SUCCESS, data, new Date());
    }

    public static <T> SuyhResult<T> ofSuccess() {
        return new SuyhResult<>(SuyhResult.OK, SUCCESS, new Date());
    }

    public static <T> SuyhResult<T> ofFail(Integer code, String msg) {
        return new SuyhResult<>(code, msg, new Date());
    }

    public static SuyhResult<Boolean> ofFail(String msg) {
        return new SuyhResult<>(-1, msg, new Date());
    }

    public static <T> SuyhResult<T> ofResultCode(ErrorCode code) {
        return new SuyhResult<>(code.getCode(), code.getDesc(), new Date());
    }

    public static <T> SuyhResult<T> ofResultCode(ErrorCode code, T data) {
        return new SuyhResult<>(code.getCode(), code.getDesc(), data, new Date());
    }
}

