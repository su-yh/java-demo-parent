package com.suyh5601.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private T data;
    private int status = 0;
    private String message = "SUCCESS";
    private Date timestamp = new Date();

    public BaseResponse(String message) {
        this.message = message;
    }

    public BaseResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> BaseResponse<T> ok() {
        return new BaseResponse<>("操作成功");
    }

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<T>("操作成功").setData(data);
    }

}

