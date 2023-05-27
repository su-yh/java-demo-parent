package com.suyh2103.exception;

import com.suyh2103.vo.ErrorCode;

/**
 * 系统异常
 * 服务器产生的错误，而抛出的异常
 */
public class SystemException extends AbstractException {
    private static final long serialVersionUID = -8178059792722975716L;

    public SystemException(ErrorCode response, String extraDesc) {
        super(response, extraDesc);
    }

    public SystemException(ErrorCode response, String extraDesc, Throwable cause) {
        super(response, extraDesc, cause);
    }
}
