@Component
public class SuyhWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void extendHandlerExceptionResolvers(@NonNull List<HandlerExceptionResolver> resolvers) {
        // 添加自定义的异常处理解析器，在spring mvc 提供的默认处理器之后。
        resolvers.add(new SuyhHandlerExceptionResolver());
    }
}
