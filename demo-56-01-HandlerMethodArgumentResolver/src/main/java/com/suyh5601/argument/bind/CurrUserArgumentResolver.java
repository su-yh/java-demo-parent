package com.suyh5601.argument.bind;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CurrUserArgumentResolver implements HandlerMethodArgumentResolver {

    // 只有标注有CurrUser注解，并且数据类型是CurrUserVo/Map/Object的才给与处理
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        CurrLoginUser ann = parameter.getParameterAnnotation(CurrLoginUser.class);
        Class<?> parameterType = parameter.getParameterType();
        return (ann != null &&
                (CurrUserVo.class.isAssignableFrom(parameterType)
                        || Map.class.isAssignableFrom(parameterType)
                        || Object.class.isAssignableFrom(parameterType)));
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        // 获取到拦截器放到属性中的user 对象
        return request.getAttribute("currLoginUser");
    }
}


