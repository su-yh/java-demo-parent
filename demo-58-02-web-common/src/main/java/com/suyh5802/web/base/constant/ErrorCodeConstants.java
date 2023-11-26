package com.suyh5802.web.base.constant;

/**
 * @author suyh
 * @since 2023-11-26
 */
public interface ErrorCodeConstants {
    ErrorStatus PARAMETER_ERROR = new ErrorStatus(1_015_000_000, "参数错误");
    // 带参数的错误
    ErrorStatus PARAMETER_ERROR_PARAM = new ErrorStatus(1_015_000_001, "参数错误: {}");
}
