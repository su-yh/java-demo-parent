package com.mastermile.proxy.constant;

/**
 * @author suyh
 * @since 2023-11-26
 */
public interface ErrorCodeConstants {
    ErrorStatus PARAMETER_ERROR = new ErrorStatus(1_015_000_000, "参数错误");
    ErrorStatus PARAMETER_ERROR_PARAM = new ErrorStatus(1_015_000_001, "参数错误: {}");
}
