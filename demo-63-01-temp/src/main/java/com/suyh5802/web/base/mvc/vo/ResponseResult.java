package com.suyh5802.web.base.mvc.vo;

import com.suyh5802.web.base.constant.ErrorCode;
import lombok.Getter;

@Getter
public class ResponseResult<T> extends BaseResult {

    private final Integer errorCode;

    private final T data;

    private ResponseResult(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.data = null;
    }

    private ResponseResult(ErrorCode errorCode, T data) {
        this.errorCode = errorCode.getCode();
        this.data = data;
    }

    public static <T> ResponseResult<T> ofSuccess(T data) {
        return new ResponseResult<>(ErrorCode.SUCCESS, data);
    }

    public static <T> ResponseResult<T> ofSuccess() {
        return new ResponseResult<>(ErrorCode.SUCCESS);
    }

    public static <T> ResponseResult<T> ofFail(ErrorCode errorCode) {
        return new ResponseResult<>(errorCode);
    }
}
