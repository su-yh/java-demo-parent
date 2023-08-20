package com.suyh5801.mvc.exception;

import com.suyh5801.constant.SuyhCode;

/**
 * 业务异常
 * 由后端业务产生的响应数据，结果为正常数据
 */
public class SuyhBusinessException extends AbstractException {
    private static final long serialVersionUID = -8033733099820550575L;

    public SuyhBusinessException(SuyhCode response) {
        super(response, null);
    }

    public SuyhBusinessException(SuyhCode response, String extraDesc) {
        super(response, extraDesc);
    }

    public SuyhBusinessException(SuyhCode response, Throwable cause) {
        super(response, null, cause);
    }

    public SuyhBusinessException(SuyhCode response, String extraDesc, Throwable cause) {
        super(response, extraDesc, cause);
    }
}
