package com.suyh5802.web.base.mvc.exception;

import com.suyh5802.web.base.constant.ErrorStatus;
import lombok.Getter;
import org.springframework.lang.NonNull;

public class OverseasProxyException extends RuntimeException {
    private static final long serialVersionUID = -4365987799788427525L;

    @Getter
    private final Integer errorCode;

    @Getter
    private final String message;

    @Getter
    private final ExceptionCategory category;

    public OverseasProxyException(@NonNull ExceptionCategory category, @NonNull ErrorStatus errorStatus, Object... params) {
        super(errorStatus.getMsg());

        this.category = category;
        String message = org.slf4j.helpers.MessageFormatter.arrayFormat(errorStatus.getMsg(), params).getMessage();
        this.errorCode = errorStatus.getCode();
        this.message = message;
    }
}
