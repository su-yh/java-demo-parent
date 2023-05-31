
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常的处理方式
 * 其他的异常处理放到默认处理异常里面。
 * 
 * 可能这个真的不适合在这里使用，不要它了吧。
 * 使用自定义的 HandlerExceptionResolver 来处理自己的异常，其他的异常还是交给spring mvc 为我们提供的默认处理器。
 */
// @RestControllerAdvice
@Slf4j
public class SuyhRestControllerAdvice {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DemoException.class)
    SuyhResult<String> handleControllerException(DemoException exception) {
        log.error("handleControllerException: {}", exception.getMessage());
        return SuyhResult.ofFail(-1, SuyhException.INTERNAL_ERROR);
    }
}
