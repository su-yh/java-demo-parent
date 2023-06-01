package com.suyh5801.mvc.error;

import com.suyh5801.mvc.exception.SuyhBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 派生成spring mvc 默认异常处理类
 * 添加一些自定义的响应属性值。
 *
 * 参考：ErrorMvcAutoConfiguration#errorAttributes() 方法，创建的一个bean 对象
 * 这里的方法也是规划该方法的bean 实现，并在生成响应体里扩展自己的一些属性。
 * 但是如果是自定义的异常最好还是添加一个ControllerAdvice 的全局性的单独处理。
 *
 *
 * 4xx 5xx 都是错误，其他都不被识别为错误。
 * 但是200 的需要判断，最终都是通过 success 的boolean 结果判断成功与失败。
 * 以前的code 现在已经不用了
 * 现在的status 与HttpStatus 一致
 */
@Component
@Slf4j
public class SuyhErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        final String timestampFormat = sdf.format(new Date());

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("timestamp", timestampFormat);
        errorAttributes.put("success", false);

        Throwable throwable = getError(webRequest);

        if (!SuyhBusinessException.class.isAssignableFrom(throwable.getClass())) {
            log.warn("timestamp: {}", timestampFormat, throwable);
        }

        return errorAttributes;
    }
}
