package com.suyh5802.web.base.mvc.exception;

import com.suyh5802.web.base.constant.ErrorStatus;
import org.springframework.lang.NonNull;

public class ExceptionUtil {

    public static OverseasProxyException business(@NonNull ErrorStatus errorStatus, Object... params) {
        return new OverseasProxyException(ExceptionCategory.BUSINESS, errorStatus, params);
    }

    public static OverseasProxyException system(@NonNull ErrorStatus errorStatus, Object... params) {
        return new OverseasProxyException(ExceptionCategory.SYSTEM, errorStatus, params);
    }
}
