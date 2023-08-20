package com.suyh5801.mvc.exception;

import com.suyh5801.constant.SuyhCode;

/**
 * 系统异常
 * 服务器产生的错误，而抛出的异常
 */
public class SuyhSystemException extends AbstractException {
    private static final long serialVersionUID = -8178059792722975716L;

    public SuyhSystemException(SuyhCode response) {
        super(response, null);
    }

    public SuyhSystemException(SuyhCode response, String extraDesc) {
        super(response, extraDesc);
    }

    public SuyhSystemException(SuyhCode response, Throwable cause) {
        super(response, null, cause);
    }

    public SuyhSystemException(SuyhCode response, String extraDesc, Throwable cause) {
        super(response, extraDesc, cause);
    }
}
