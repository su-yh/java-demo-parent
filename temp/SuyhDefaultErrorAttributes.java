

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
 */
@Component
@Slf4j
public class SuyhDefaultErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        final String timestampFormat = sdf.format(new Date());
        errorAttributes.put("timestamp", timestampFormat);
        errorAttributes.put("success", false);

        final Throwable throwable = getError(webRequest);
        if (throwable instanceof DemoException) {
            // 将error 重新写到map 中，因为DemoException 的错误码被处理成200 ，对应的error 的值是：OK.
            errorAttributes.put("error", throwable.getMessage());
            log.info("demoException, timestamp: {}", timestampFormat);
        } else {
            log.error("timestamp: {}", timestampFormat, getError(webRequest));
        }

        return errorAttributes;
    }
}
