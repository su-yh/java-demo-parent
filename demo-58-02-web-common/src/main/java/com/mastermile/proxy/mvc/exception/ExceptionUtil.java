package com.mastermile.proxy.mvc.exception;

import com.mastermile.proxy.constant.ErrorStatus;
import org.springframework.lang.NonNull;

public class ExceptionUtil {

    public static OverseasProxyException business(@NonNull ErrorStatus errorStatus, Object... params) {
        return new OverseasProxyException(ExceptionCategory.BUSINESS, errorStatus, params);
    }

    public static OverseasProxyException system(@NonNull ErrorStatus errorStatus, Object... params) {
        return new OverseasProxyException(ExceptionCategory.SYSTEM, errorStatus, params);
    }
}
