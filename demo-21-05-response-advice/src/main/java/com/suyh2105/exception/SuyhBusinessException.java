package com.suyh2105.exception;

import com.suyh2105.response.ResultCode;

/**
 * 业务异常
 * 由后端业务产生的响应数据，结果为正常数据
 */
public class SuyhBusinessException extends AbstractException {
    private static final long serialVersionUID = -8033733099820550575L;

    public SuyhBusinessException(ResultCode response) {
        super(response, null);
    }

    public SuyhBusinessException(ResultCode response, String extraDesc) {
        super(response, extraDesc);
    }

    public SuyhBusinessException(ResultCode response, Throwable cause) {
        super(response, null, cause);
    }

    public SuyhBusinessException(ResultCode response, String extraDesc, Throwable cause) {
        super(response, extraDesc, cause);
    }
}
