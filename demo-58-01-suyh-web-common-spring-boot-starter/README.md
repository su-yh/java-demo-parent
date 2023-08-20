统一异常值处理，但是不使用@ControllerAdvice 的方式

拦截返回值处理，增强为`SuyhResult<T>` 泛型，而不需要在controller 方法的返回值上写明该泛型

DefaultHandlerExceptionResolver 里面好像只处理spring mvc 自己抛出的异常，并没有处理其他的异常，那这部分异常是由谁处理了呢？



```java
// 记录一些默认异常处理的关键类

// 它也是一个异常处理解析类，不过它里面包含了三个真正的异常处理解析类
HandlerExceptionResolverComposite implement HandlerExceptionResolver

    @Nullable
    private List<HandlerExceptionResolver> resolvers = {
        // 正常情况下它的优先级会比较高，@ControllerAdvice 的异常处理方法就在它里面处理的
        ExceptionHandlerExceptionResolver

        // 不太了解
        ResponseStatusExceptionResolver

        // spring mvc 提供的默认异常处理类，当前面的都没有处理了之后就会流到该类处理
        DefaultHandlerExceptionResolver
    }







// spring mvc 的分发处理类
DispatcherServlet

    // 异常处理解析类，但是上面那三个并不在这里存储
    // HandlerExceptionResolverComposite 就在该属性里面的
    @Nullable
	private List<HandlerExceptionResolver> handlerExceptionResolvers;






// 默认的异常会走该controller 
BasicErrorController
```




