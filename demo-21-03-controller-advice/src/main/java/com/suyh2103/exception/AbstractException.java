package com.suyh2103.exception;

import com.suyh2103.vo.ErrorCode;
import lombok.Getter;

public abstract class AbstractException extends RuntimeException {
    private static final long serialVersionUID = -4365987799788427525L;

    @Getter
    private final ErrorCode errorCode;
    @Getter
    private final String extraDesc;

    public AbstractException(ErrorCode response, String extraDesc) {
        super(response.getDesc());
        this.errorCode = response;
        this.extraDesc = extraDesc;
    }

    public AbstractException(ErrorCode response, String extraDesc, Throwable cause) {
        super(response.getDesc(), cause);
        this.errorCode = response;
        this.extraDesc = extraDesc;
    }
}
