package com.suyh5601.argument.bind;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.ValidationAnnotationUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * 这也是一个参数解析器的实现，同时它还对该对数进行了validator 校验，如果不通过直接抛异常告诉调用者参数不合法。
 */
public class UserArgumentResolverAndValidation implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        UserParameter ann = parameter.getParameterAnnotation(UserParameter.class);
        if (ann == null) {
            return false;
        }
        Class<?> parameterType = parameter.getParameterType();
        return CurrUserVo.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        CurrUserVo attribute = new CurrUserVo();
        attribute.setName(request.getParameter("name"));

        String idParam = request.getParameter("id");
        if (StringUtils.hasText(idParam)) {
            long id = Long.parseLong(idParam);
            attribute.setId(id);
        }

        if (binderFactory != null) {
            if (attribute != null) {
                String name = ModelFactory.getNameForParameter(parameter);
                WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
                validateIfApplicable(binder, parameter);
                if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
                    throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
                }
            }
        }

        return attribute;
    }

    /**
     * 参考{@link RequestResponseBodyMethodProcessor} 或者 {@link ModelAttributeMethodProcessor}
     */
    protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
        for (Annotation ann : parameter.getParameterAnnotations()) {
            Object[] validationHints = ValidationAnnotationUtils.determineValidationHints(ann);
            if (validationHints != null) {
                binder.validate(validationHints);
                break;
            }
        }
    }

    /**
     * 参考{@link RequestResponseBodyMethodProcessor} 或者 {@link ModelAttributeMethodProcessor}
     */
    protected boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter parameter) {
        int i = parameter.getParameterIndex();
        Class<?>[] paramTypes = parameter.getExecutable().getParameterTypes();
        boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));
        return !hasBindingResult;
    }
}
