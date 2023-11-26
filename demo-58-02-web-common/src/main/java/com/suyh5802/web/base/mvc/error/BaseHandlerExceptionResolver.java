package com.suyh5802.web.base.mvc.error;

import com.suyh5802.web.base.mvc.exception.ExceptionCategory;
import com.suyh5802.web.base.mvc.exception.BaseException;
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
public class BaseHandlerExceptionResolver extends DefaultHandlerExceptionResolver {

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

        try {
            // spring mvc 不识别且没处理的异常，我们自己处理。
            if (ex instanceof BaseException) {
                return handleOverseasProxyException((BaseException) ex, request, response, handler);
            }

            // 剩下的所有异常处理，都由它来解决。这些剩下的异常我们自己也不识别，所以放在这里处理。
            return handlerException(ex, request, response, handler);
        } catch (Exception handlerEx) {
            log.warn("Failure while trying to resolve exception [" + ex.getClass().getName() + "]", handlerEx);
        }

        return null;
    }

    protected ModelAndView handleOverseasProxyException(
            @NonNull BaseException ex, @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @Nullable Object handler) throws IOException {
        // 处理自定义的异常类，这里将业务异常认为是200 的OK，对于error 的返回值属性将在ErrorAttributes 中处理
        // OverseasProxyException 因为是自定义的异常类，这个异常表示的是请求是正常的，只是业务失败，所以要将状态处理成200。然后报业务错误即可。
        int errorCode = ExceptionCategory.SYSTEM.equals(ex.getCategory()) ? HttpServletResponse.SC_INTERNAL_SERVER_ERROR : HttpServletResponse.SC_OK;

        response.sendError(errorCode, ex.getMessage());
        return new ModelAndView();
    }

    protected ModelAndView handlerException(
            @NonNull Exception ex, @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @Nullable Object handler) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "service error.");
        return new ModelAndView();
    }
}
