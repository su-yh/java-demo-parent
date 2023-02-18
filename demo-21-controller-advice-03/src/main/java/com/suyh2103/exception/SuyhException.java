package com.suyh2103.exception;

import com.suyh2103.vo.ErrorCode;
import lombok.Getter;

public class SuyhException extends RuntimeException {
    private static final long serialVersionUID = -8033733099820550575L;

    @Getter
    private final ErrorCode errorCode;

    public SuyhException(ErrorCode response) {
        super(response.getDesc());
        this.errorCode = response;
    }

    public SuyhException(ErrorCode response, Throwable cause) {
        super(response.getDesc(), cause);
        this.errorCode = response;
    }
}
