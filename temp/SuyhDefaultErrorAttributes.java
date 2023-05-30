

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
 */
@Component
@Slf4j
public class SuyhDefaultErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        log.error("throwable", getError(webRequest));

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        final String timestampFormat = sdf.format(new Date());
        errorAttributes.put("timestamp", timestampFormat);
        errorAttributes.put("success", false);
        return errorAttributes;
    }
}
