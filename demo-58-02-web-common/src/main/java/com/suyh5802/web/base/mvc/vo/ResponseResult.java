package com.suyh5802.web.base.mvc.vo;

import com.suyh5802.web.base.constant.ErrorStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseResult<T> extends BaseResult {

    @Schema(description = "业务上的错误码，0 表示成功，其他表示失败。")
    private final Integer errorCode;

    @Schema(description = "成功时的响应数据，status 的值为2xx，同时errorCode 的值为0 时，该值才有效，否则没有意义。")
    private final T data;

    private ResponseResult(ErrorStatus errorStatus) {
        super(HttpStatus.OK.value(), errorStatus.getMsg());

        this.errorCode = errorStatus.getCode();
        this.data = null;
    }

    private ResponseResult(ErrorStatus errorStatus, T data) {
        super(HttpStatus.OK.value(), errorStatus.getMsg());

        this.errorCode = errorStatus.getCode();
        this.data = data;
    }

    public static <T> ResponseResult<T> ofSuccess(T data) {
        return new ResponseResult<>(ErrorStatus.SUCCESS, data);
    }

    public static <T> ResponseResult<T> ofSuccess() {
        return new ResponseResult<>(ErrorStatus.SUCCESS);
    }

    public static <T> ResponseResult<T> ofFail(ErrorStatus errorStatus) {
        return new ResponseResult<>(errorStatus);
    }
}
