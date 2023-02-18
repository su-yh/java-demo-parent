package com.suyh2103.exception;

import com.suyh2103.vo.ErrorCode;

/**
 * 业务异常，前端需要处理的异常。
 * 由后端业务产生的响应数据。
 */
public class BusinessException extends AbstractException {
    private static final long serialVersionUID = -8033733099820550575L;

    public BusinessException(ErrorCode response, String extraDesc) {
        super(response, extraDesc);
    }

    public BusinessException(ErrorCode response, String extraDesc, Throwable cause) {
        super(response, extraDesc, cause);
    }
}
