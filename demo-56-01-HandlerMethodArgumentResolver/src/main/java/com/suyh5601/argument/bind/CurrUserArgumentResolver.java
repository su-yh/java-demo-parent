package com.suyh5601.argument.bind;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CurrUserArgumentResolver implements HandlerMethodArgumentResolver {

    // 只有标注有CurrUser注解，并且数据类型是CurrUserVo/Map/Object的才给与处理
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        CurrUser ann = parameter.getParameterAnnotation(CurrUser.class);
        Class<?> parameterType = parameter.getParameterType();
        return (ann != null &&
                (CurrUserVo.class.isAssignableFrom(parameterType)
                        || Map.class.isAssignableFrom(parameterType)
                        || Object.class.isAssignableFrom(parameterType)));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        // 从请求头中拿到token
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            return null; // 此处不建议做异常处理，因为校验token的事不应该属于它来做，别好管闲事
        }

        // 此处作为测试：new一个处理（写死的）
        CurrUserVo userVo = new CurrUserVo();
        userVo.setId(1L);
        userVo.setName("fsx");

        // 判断参数类型进行返回
        Class<?> parameterType = parameter.getParameterType();
        if (Map.class.isAssignableFrom(parameterType)) {
            Map<String, Object> map = new HashMap<>();
            BeanUtils.copyProperties(userVo, map);
            return map;
        } else {
            return userVo;
        }

    }
}


