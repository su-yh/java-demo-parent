package com.suyh5802.web.base.mvc.vo;

import com.suyh5802.web.base.constant.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ResponseResult<T> extends BaseResult {

    @Schema(description = "业务上的错误码，0 表示成功，其他表示失败。只有在status 的值为2xx 的时候该值才有效。")
    private final Integer errorCode;

    @Schema(description = "成功时的响应数据，status 的值为2xx，同时errorCode 的值为0 时，该值才有效，否则没有意义。")
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
