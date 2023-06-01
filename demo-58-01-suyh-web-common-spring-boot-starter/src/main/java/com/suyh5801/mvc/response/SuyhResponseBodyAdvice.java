package com.suyh5801.mvc.response;

import com.suyh5801.util.JsonUtils;
import com.suyh5801.vo.SuyhResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// TODO: suyh - 这里是必须这个注解还是说随便是一个bean 就可以的？？？
@ControllerAdvice
public class SuyhResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return !ResponseEntity.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response) {

        // 遇到feign接口之类的请求, 不应该再次包装,应该直接返回
        // 上述问题的解决方案: 可以在feign拦截器中,给feign请求头中添加一个标识字段, 表示是feign请求
        // 在此处拦截到feign标识字段, 则直接放行 返回body.

        if (body == null) {
            return SuyhResult.ofSuccess();
        }

        if (body instanceof SuyhResult) {
            return body;
        }

        // 这里需要对String 类型的做特别处理，因为它由StringHttpMessageConverter 类处理，
        // 如果返回的是SuyhResult 会发生类型转换异常。
        if (String.class.isAssignableFrom(body.getClass())) {
            SuyhResult<Object> result = SuyhResult.ofSuccess(body);
            return JsonUtils.serializable(result);
        }

        return SuyhResult.ofSuccess(body);
    }
}