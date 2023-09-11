package com.suyh2106.mvc.error;

import com.suyh2106.mvc.exception.SuyhBusinessException;
import com.suyh2106.mvc.exception.SuyhSystemException;
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
        // 如果spring mvc 能够识别的异常，就先闪给spring mvc 来处理，识别不了并没有处理的异常，这里将会返回null。
        ModelAndView mav = super.doResolveException(request, response, handler, ex);
        if (mav != null) {
            return mav;
        }

        // spring mvc 不识别且没处理的异常，我们自己处理。
        try {
            if (ex instanceof SuyhBusinessException) {
                return handleBusinessException((SuyhBusinessException) ex, request, response, handler);
            }
            if (ex instanceof SuyhSystemException) {
                return handleSystemException((SuyhSystemException) ex, request, response, handler);
            }

            // 剩下的所有异常处理，都由它来解决。这些剩下的异常我们自己也不识别，所以放在这里处理。
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
