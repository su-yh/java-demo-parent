package com.suyh4401.method.annotation;

import com.suyh4401.bind.annotation.RequestPropertiesBody;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.objenesis.instantiator.util.ClassUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * 参考博客：https://blog.csdn.net/guanzhengyinqin/article/details/85255840
 * 对于RequestResponseBodyMethodProcessor，参考博客:
 * 1. https://blog.csdn.net/qq_33012981/article/details/113860575
 * 2. https://blog.csdn.net/u013219087/article/details/109665527
 */
public class RequestParamMethodPropertiesBodyArgumentConvertObjectResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 只要在请求参数前面添加了该注解，该类就支持解析处理。
        return parameter.hasParameterAnnotation(RequestPropertiesBody.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> parameterType = parameter.getParameterType();
        Properties properties = getProperties(webRequest);
        Object arg = null;

        // 如果是基本数据类型直接返回
        if (isPrimitive(parameterType)) {
            return getArg(parameterType, properties.getProperty(Objects.requireNonNull(parameter.getParameterName())));
        }

        // 如果是String 直接返回
        if (String.class.equals(parameterType)) {
            return properties.getProperty(Objects.requireNonNull(parameter.getParameterName()));
        }

        // 获取字段
        Field[] fields = parameterType.getDeclaredFields();
        // 实例化对象
        Object result = ClassUtils.newInstance(parameterType);
        // 往对象写入值
        for (Field field : fields) {
            arg = properties.getProperty(field.getName());
            if (arg == null) {
                continue;
            }
            if (isPrimitive(field.getType())) {
                Class<?> parType = field.getType();
                arg = getArg(parType, arg);
            } else {
                // TODO: 非基元类型还需要另外处理
            }
            Method setter = getSetterMethod(parameterType, field);
            if (setter != null) {
                setter.invoke(result, arg);
            }
        }
        return result;
    }

    private Method getSetterMethod(Class<?> clazz, Field field) throws NoSuchMethodException {
        return clazz.getDeclaredMethod("set" + toUpperCaseFirstOne(field.getName()), field.getType());
    }

    private boolean isPrimitive(Class<?> clazz) {
        return Integer.class.equals(clazz) ||
                Double.class.equals(clazz) ||
                Float.class.equals(clazz) ||
                Boolean.class.equals(clazz) ||
                Long.class.equals(clazz) ||
                Byte.class.equals(clazz) ||
                Short.class.equals(clazz) ||
                clazz.isPrimitive();
    }

    private Properties getProperties(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        Properties properties = new Properties();
        if (request != null) {
            // Servlet Request API
            String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
            // 获取 Content-Type 请求头
            MediaType mediaType = MediaType.parseMediaType(contentType);
            Charset charset = mediaType.getCharset();

            // 当charset 为空时，就使用UTF-8
            charset = charset == null ? StandardCharsets.UTF_8 : charset;

            // 字节流
            InputStream inputStream = request.getInputStream();

            InputStreamReader reader = new InputStreamReader(inputStream, charset);

            // 加载字符流成为 Properties 对象
            properties.load(reader);

            inputStream.close();
        } else {
            throw new IllegalArgumentException();
        }

        return properties;
    }

    private Object getArg(Class<?> primitiveClass, Object value)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object result = null;
        if (isPrimitive(primitiveClass)) {
            String primitiveName = primitiveClass.getSimpleName();
            if ("char".equals(primitiveName)) {
                return value;
            }
            String firstUpperPrimitiveName = toUpperCaseFirstOne(primitiveName);
            Class<?> encapsulationClass = "int".equals(primitiveName)
                    ? Class.forName("java.lang.Integer")
                    : Class.forName("java.lang." + firstUpperPrimitiveName);
            result = encapsulationClass.getDeclaredMethod("valueOf", String.class).invoke(null, value);
        }
        return result;
    }

    private String toUpperCaseFirstOne(String fieldName) {
        if (Character.isUpperCase(fieldName.charAt(0))) {
            return fieldName;
        } else {
            return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        }
    }
}
