package com.suyh2103.controller.advice;

import com.suyh2103.exception.SuyhException;
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

    @ExceptionHandler(value = {SuyhException.class})
    SuyhResult<String> handleControllerException(SuyhException exception) {
        return SuyhResult.ofResultCode(exception.getErrorCode(), "Business exception");
    }

    @ExceptionHandler(value = Exception.class)
    public SuyhResult<String> handleException(Exception exception, HttpServletResponse response) {
        log.error("exception", exception);
        response.setStatus(500);
        return SuyhResult.ofResultCode(ErrorCode.ERROR, exception.getClass().getSimpleName());
    }
}
