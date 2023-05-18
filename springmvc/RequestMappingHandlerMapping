RequestMappingHandlerMapping

// 解析对应的一个 handler bean 所有的方法
protected void detectHandlerMethods(Object handler) {
    // 传入的参数没有明确指定是bean 对象或者bean name，但是可以肯定的是他一定代表着一个controller 的bean 对象
    // 正常情况，这里传入的就是一个bean name.
    // 最终拿到它对应的Class 
    Class<?> handlerType = (handler instanceof String ?
            obtainApplicationContext().getType((String) handler) : handler.getClass());


    if (handlerType != null) {
        // 处理这个类，因为这个类很有可能是一个cglib 的代理类，而用户定义的原生类
        Class<?> userType = ClassUtils.getUserClass(handlerType);
        
        // 这里对该类(及其父类包括接口)的所有方法进行遍历，并对该方法执行给定逻辑，最终返回期望的元数据
        Map<Method, T> methods = MethodIntrospector.selectMethods(userType,
                (MethodIntrospector.MetadataLookup<T>) method -> {
                    try {
                        // 解析这个方法所对应的url 路径
                        return getMappingForMethod(method, userType);
                    }
                    catch (Throwable ex) {
                        throw new IllegalStateException("Invalid mapping on handler class [" +
                                userType.getName() + "]: " + method, ex);
                    }
                });
        if (logger.isTraceEnabled()) {
            logger.trace(formatMappings(userType, methods));
        }
        else if (mappingsLogger.isDebugEnabled()) {
            mappingsLogger.debug(formatMappings(userType, methods));
        }
        
        // 将这些方法进行遍历，遍历之后将url 与方法的映射注册保存下来。后续使用。
        methods.forEach((method, mapping) -> {
            Method invocableMethod = AopUtils.selectInvocableMethod(method, userType);
            // 将这个url 映射与方法进行关联，并注册在RequestMappingHandlerMapping(实际在内部类 AbstractHandlerMethodMapping#mappingRegistry) 中。
            registerHandlerMethod(handler, invocableMethod, mapping);
        });
    }
}
