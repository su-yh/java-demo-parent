package com.suyh5802.web.base.mvc.exception;

import com.suyh5802.web.base.constant.ErrorCode;
import org.springframework.lang.NonNull;

public class ExceptionUtil {

    public static BaseException business(@NonNull ErrorCode errorCode, Object... params) {
        return new BaseException(ExceptionCategory.BUSINESS, errorCode, params);
    }

    public static BaseException system(@NonNull ErrorCode errorCode, Object... params) {
        return new BaseException(ExceptionCategory.SYSTEM, errorCode, params);
    }
}
