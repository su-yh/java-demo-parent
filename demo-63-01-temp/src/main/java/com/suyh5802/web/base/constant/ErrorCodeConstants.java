package com.suyh5802.web.base.constant;

/**
 * @author suyh
 * @since 2023-11-26
 */
public interface ErrorCodeConstants {
    ErrorCode PARAMETER_ERROR = new ErrorCode(1015001, "参数错误");
    // 带参数的错误
    ErrorCode PARAMETER_ERROR_PARAM = new ErrorCode(1015002, "参数错误: {0}");

    ErrorCode USER_NOT_EXISTS = new ErrorCode(1015003, "用户(id: {0})不存在");
}
