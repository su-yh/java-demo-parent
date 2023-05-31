// 记录一些默认异常处理的关键类


HandlerExceptionResolverComposite#resolvers
    ExceptionHandlerExceptionResolver
    ResponseStatusExceptionResolver
    DefaultHandlerExceptionResolver


DispatcherServlet
    	@Nullable
	private List<HandlerExceptionResolver> handlerExceptionResolvers;
  

// 默认的异常会走该controller 
BasicErrorController
