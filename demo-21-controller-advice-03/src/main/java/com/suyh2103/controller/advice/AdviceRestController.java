package com.suyh2103.controller.advice;

import com.suyh2103.exception.BusinessException;
import com.suyh2103.exception.SystemException;
import com.suyh2103.vo.ErrorCode;
import com.suyh2103.vo.SuyhResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 所有的这里的响应码都是200 成功
 */
@RestControllerAdvice
@Slf4j
public class AdviceRestController {

    @ExceptionHandler(value = {BusinessException.class})
    public SuyhResult<String> handleBusinessException(BusinessException exception) {
        return SuyhResult.ofResultCode(exception.getErrorCode(), exception.getExtraDesc());
    }

    @ExceptionHandler(value = {SystemException.class})
    public SuyhResult<String> handleSystemException(SystemException exception, HttpServletResponse response) {
        log.error("system exception.", exception);
        response.setStatus(500);
        return SuyhResult.ofResultCode(exception.getErrorCode(), exception.getExtraDesc());
    }

    @ExceptionHandler(value = Exception.class)
    public SuyhResult<String> handleException(Exception exception, HttpServletResponse response) {
        log.error("unknown exception.", exception);
        response.setStatus(500);
        return SuyhResult.ofResultCode(ErrorCode.ERROR, exception.getClass().getSimpleName());
    }
}
