package com.suyh5801.mvc.exception;

import com.suyh5801.constant.SuyhCode;

public abstract class AbstractException extends RuntimeException {
    private static final long serialVersionUID = -4365987799788427525L;

    private final SuyhCode errorCode;
    private final String extraDesc;

    public AbstractException(SuyhCode response, String extraDesc) {
        super(response.getDesc());
        this.errorCode = response;
        this.extraDesc = extraDesc;
    }

    public AbstractException(SuyhCode response, String extraDesc, Throwable cause) {
        super(response.getDesc(), cause);
        this.errorCode = response;
        this.extraDesc = extraDesc;
    }
}
