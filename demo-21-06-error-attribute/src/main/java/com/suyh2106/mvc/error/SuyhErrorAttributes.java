package com.suyh2106.mvc.error;

import com.suyh2106.mvc.exception.SuyhBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 派生自spring mvc 默认异常处理类
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
// 这里因为我们将自己实现的错误处理类注册成了一个bean 对象，那么就会替换掉spring mvc 的默认错误属性(DefaultErrorAttributes).
// 如果没有被替换，那么就要注意一个优先级，或者扫描的情况。
// 参考：ErrorMvcAutoConfiguration
@Component
@Slf4j
public class SuyhErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        final String timestampFormat = sdf.format(new Date());

        // 优先走spring mvc 的默认异常处理类
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        // 然后再添加自己的一些返回属性。
        // 这样既兼容了spring mvc 的异常处理，我们还不需要处理太多的异常，对每一个异常都要进行拦截处理。(相比与@RestControllerAdvice 或者@ControllerAdvice)
        // 但是它们(@RestControllerAdvice 和@ControllerAdvice) 的优先级会更高，如果被它们处理了，就不会走到这里来了。
        errorAttributes.put("timestamp", timestampFormat);  // 这里我们将默认的时间戳改成我们自己想要的那种时间戳格式
        errorAttributes.put("success", false);  // 在这里我们添加自己想要的属性。

        Throwable throwable = getError(webRequest);

        if (!SuyhBusinessException.class.isAssignableFrom(throwable.getClass())) {
            // 在这里我们打印一下异常的堆栈信息。
            log.warn("timestamp: {}", timestampFormat, throwable);
        }

        return errorAttributes;
    }
}
