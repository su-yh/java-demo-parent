package com.suyh5802.web.base.mvc.exception;

import com.suyh5802.web.base.constant.ErrorStatus;
import org.springframework.lang.NonNull;

public class ExceptionUtil {

    public static BaseException business(@NonNull ErrorStatus errorStatus, Object... params) {
        return new BaseException(ExceptionCategory.BUSINESS, errorStatus, params);
    }

    public static BaseException system(@NonNull ErrorStatus errorStatus, Object... params) {
        return new BaseException(ExceptionCategory.SYSTEM, errorStatus, params);
    }
}
