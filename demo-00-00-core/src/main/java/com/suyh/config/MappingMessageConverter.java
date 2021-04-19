package com.suyh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyh.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 自定义重写@RequestBody 的json 映射到实体
 *
 * @author sWX5327794
 * @since 2021-04-16
 */
@Slf4j
public class MappingMessageConverter extends MappingJackson2HttpMessageConverter {
    public MappingMessageConverter() {
    }

    public MappingMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 表明只处理UserEntity类型的参数。
        // return UserEntity.class.isAssignableFrom(clazz);
        return super.supports(clazz);
    }

    /**
     * 这个是不是也是其中的一种调用方式呢？
     * 因为我看父类里面这两个方法都调用了同一个处理方法
     * AbstractJackson2HttpMessageConverter#readJavaType(JavaType, HttpInputMessage)
     */
    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Object entity = super.read(type, contextClass, inputMessage);
        try {
            ClassUtils.strTrim(entity, entity.getClass());
        } catch (Exception e) {
            log.warn("strTrim failed", e);
        }
        return entity;
    }



    /**
     * 重写readlntenal 方法，处理请求的数据。代码表明我们处理由“-”隔开的数据，并转成 UserEntity类型的对象。
     */
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Object entity = super.readInternal(clazz, inputMessage);

        // TODO: suyh - 在这里对实体的字符串的处理
        /*
         * 这里可以利用反射，将所有的字符串类型的属性进行剔除空白字符的实现。
         * 还可以添加一个自己的注解，用于保留空白字符。
         */
        return entity;
    }

    /**
     * 重写writeInternal ，处理如何输出数据到response。
     */
    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        super.writeInternal(o, outputMessage);
    }
}
