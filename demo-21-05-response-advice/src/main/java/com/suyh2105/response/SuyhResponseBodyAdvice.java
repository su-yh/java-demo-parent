package com.suyh2105.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyh2105.annotation.DisableControllerResponseAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * 该接口的实现原理在 AbstractMessageConverterMethodProcessor#writeWithMessageConverters(Object, MethodParameter, ServletServerHttpRequest, ServletServerHttpResponse) writeWithMessageConverters(..)
 * 里面有一行调用：getAdvice().beforeBodyWrite(..)
 * 这些advice 的值是由{@link RequestResponseBodyMethodProcessor#RequestResponseBodyMethodProcessor(List, List) RequestResponseBodyMethodProcessor(..)} 构造方法传入并初始化。
 * 它的实例化在  RequestMappingHandlerAdapter#getDefaultArgumentResolvers()
 */
@ControllerAdvice
public class SuyhResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // 已经封装了统一的返回值对象，这里就不处理了。
        if (methodParameter.getParameterType().isAssignableFrom(SuyhResult.class)) {
            return false;
        }
        // 使用注解明确禁止统一封装的，这里也不处理。
        if (methodParameter.hasMethodAnnotation(DisableControllerResponseAdvice.class)) {
            return false;
        }

        // 剩下的则需要处理
        return true;
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

        // 这里需要对String 类型的做特别处理，因为它由StringHttpMessageConverter 类处理，
        // 如果返回的是BaseResponse 会发生类型转换异常。
        if (String.class.isAssignableFrom(body.getClass())) {
            // TODO: suyh - 需要使用json 序列化一下。将序列化的结果返回出去。
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(SuyhResult.ofSuccess(body));
            } catch (JsonProcessingException e) {
                // 这里能抛出异常吗？？？
                // 这里可以抛异常，异常会被ControllerAdvice 拦截并处理，处理完了，还会再次进入当前位置。
                throw new RuntimeException(e);
            }
        }

        return SuyhResult.ofSuccess(body);
    }
}
