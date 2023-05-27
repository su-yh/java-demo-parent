package com.suyh2105.exception;

import com.suyh2105.response.ResultCode;
import lombok.Getter;

public abstract class AbstractException extends RuntimeException {
    private static final long serialVersionUID = -4365987799788427525L;

    @Getter
    private final ResultCode errorCode;
    @Getter
    private final String extraDesc;

    public AbstractException(ResultCode response, String extraDesc) {
        super(response.getDesc());
        this.errorCode = response;
        this.extraDesc = extraDesc;
    }

    public AbstractException(ResultCode response, String extraDesc, Throwable cause) {
        super(response.getDesc(), cause);
        this.errorCode = response;
        this.extraDesc = extraDesc;
    }
}
