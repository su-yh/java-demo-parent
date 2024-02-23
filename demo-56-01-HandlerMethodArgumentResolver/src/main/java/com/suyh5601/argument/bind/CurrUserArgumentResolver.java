package com.suyh5601.argument.bind;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义参数解析器的实现，该实现针对在Controller 的handler 接口方法中的参数做匹配。
 * 匹配上的参数，则会为该参数绑定上一个值，然后在handler 方法中就可以直接得到该值使用了。
 */
public class CurrUserArgumentResolver implements HandlerMethodArgumentResolver {

    // 只有标注有CurrUser注解，并且数据类型是CurrUserVo/Map/Object的handler 参数才给与处理
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        CurrLoginUser ann = parameter.getParameterAnnotation(CurrLoginUser.class);
        if (ann == null) {
            return false;
        }
        Class<?> parameterType = parameter.getParameterType();
        return (CurrUserVo.class.isAssignableFrom(parameterType)
                || Map.class.isAssignableFrom(parameterType)
                || Object.class.isAssignableFrom(parameterType));
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        // 获取到拦截器放到属性中的user 对象
        assert request != null;
        Object currLoginUser = request.getAttribute("currLoginUser");
        if (currLoginUser == null) {    // 用户未登录
            CurrLoginUser ann = parameter.getParameterAnnotation(CurrLoginUser.class);
            assert ann != null;
            if (ann.required()) {    // 用户必须登录
                throw new RuntimeException("用户未登录");
            }
        }

        return currLoginUser;
    }
}


