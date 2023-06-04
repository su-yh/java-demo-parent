package com.suyh5801.mvc.error;

import com.suyh5801.mvc.exception.SuyhBusinessException;
import com.suyh5801.mvc.exception.SuyhSystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SuyhHandlerExceptionResolver extends DefaultHandlerExceptionResolver {
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1000;
    }

    @Override
    public ModelAndView doResolveException(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            Object handler,
            @NonNull Exception ex) {
        ModelAndView mav = super.doResolveException(request, response, handler, ex);
        if (mav != null) {
            return mav;
        }

        try {
            if (ex instanceof SuyhBusinessException) {
                return handleBusinessException((SuyhBusinessException) ex, request, response, handler);
            }
            if (ex instanceof SuyhSystemException) {
                return handleSystemException((SuyhSystemException) ex, request, response, handler);
            }

            // 剩下的所有异常处理，都由它来解决。
            return handlerException(ex, request, response, handler);
        } catch (Exception handlerEx) {
            log.warn("Failure while trying to resolve exception [" + ex.getClass().getName() + "]", handlerEx);
        }

        return null;
    }

    protected ModelAndView handleBusinessException(
            @NonNull SuyhBusinessException ex, @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @Nullable Object handler) throws IOException {
        // 处理自定义的异常类，这里将业务异常认为是200 的OK，对于error 的返回值属性将在ErrorAttributes 中处理
        response.sendError(HttpServletResponse.SC_OK, ex.getMessage());
        return new ModelAndView();
    }

    protected ModelAndView handleSystemException(
            @NonNull SuyhSystemException ex, @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @Nullable Object handler) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ModelAndView();
    }

    protected ModelAndView handlerException(
            @NonNull Exception ex, @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @Nullable Object handler) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "service error.");
        return new ModelAndView();
    }
}
