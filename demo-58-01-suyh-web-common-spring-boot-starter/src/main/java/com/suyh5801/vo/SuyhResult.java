package com.suyh5801.vo;

import com.suyh5801.constant.SuyhCode;
import lombok.Data;

import java.util.Date;

@Data
public class SuyhResult<T> {
    private static Integer OK = 0;
    private static final String SUCCESS = "SUCCESS";

    private Integer status;
    private Boolean success;
    private String message;
    private Date timestamp;
    private T data;

    private SuyhResult(boolean success, String message, Date timestamp) {
        status = 200;
        this.success = success;
        this.message = message;
        this.timestamp = timestamp;
    }

    private SuyhResult(boolean success, String message, Date timestamp, T data) {
        status = 200;
        this.success = success;
        this.message = message;
        this.timestamp = timestamp;
        this.data = data;
    }

    public static <T> SuyhResult<T> ofSuccess(T data) {
        return new SuyhResult<>(true, SUCCESS, new Date(), data);
    }

    public static <T> SuyhResult<T> ofSuccess() {
        return new SuyhResult<>(true, SUCCESS, new Date());
    }

    public static <T> SuyhResult<T> ofFail(String msg) {
        return new SuyhResult<>(false, msg, new Date());
    }

    public static <T> SuyhResult<T> ofSuyhCode(SuyhCode code) {
        return new SuyhResult<>(code.isSuccess(), code.getDesc(), new Date());
    }

    public static <T> SuyhResult<T> ofSuyhCode(SuyhCode code, T data) {
        return new SuyhResult<>(code.isSuccess(), code.getDesc(), new Date(), data);
    }
}
