package com.suyh5802.web.base.mvc.exception;

import com.suyh5802.web.base.constant.ErrorCode;
import lombok.Getter;
import org.springframework.lang.NonNull;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -4365987799788427525L;

    @Getter
    private final ErrorCode errorCode;

    @Getter
    private final ExceptionCategory category;

    // errorCode 中msg 占位符中对应的参数
    @Getter
    private final Object[] params;

    public BaseException(@NonNull ExceptionCategory category, @NonNull ErrorCode errorCode, Object... params) {
        // 这样将会在message 里面显示该值。
        super(category.name() + " EXCEPTION");

        this.category = category;
        this.errorCode = errorCode;
        this.params = params;
        // 这个是使用log4j 来解析 {} 的方法，注释掉，但不要删除，免得找不到了。
        // String message = org.slf4j.helpers.MessageFormatter.arrayFormat(errorCode.getMsg(), params).getMessage();
    }
}
