package com.suyh2105.exception;

import com.suyh2105.response.ResultCode;

/**
 * 系统异常
 * 服务器产生的错误，而抛出的异常
 */
public class SuyhSystemException extends AbstractException {
    private static final long serialVersionUID = -8178059792722975716L;

    public SuyhSystemException(ResultCode response) {
        super(response, null);
    }

    public SuyhSystemException(ResultCode response, String extraDesc) {
        super(response, extraDesc);
    }

    public SuyhSystemException(ResultCode response, Throwable cause) {
        super(response, null, cause);
    }

    public SuyhSystemException(ResultCode response, String extraDesc, Throwable cause) {
        super(response, extraDesc, cause);
    }
}
