
@Slf4j
public class SuyhHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, Object handler,
            @NonNull Exception ex) {
        try {
            if (ex instanceof DemoException) {
                return handleDemoException((DemoException) ex, request, response, handler);
            }
        } catch (Exception handlerEx) {
            log.warn("Failure while trying to resolve exception [" + ex.getClass().getName() + "]", handlerEx);
        }
        return null;
    }

    
    private ModelAndView handleDemoException(
            @NonNull DemoException ex, @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @Nullable Object handler) throws IOException {
        // 处理自定义的异常类，这里将业务异常认为是200 的OK，对于error 的返回值属性将在ErrorAttributes 中处理
        response.sendError(HttpServletResponse.SC_OK, ex.getMessage());
        return new ModelAndView();
    }
}
