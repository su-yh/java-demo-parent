spring boot tomcat 源码分析参考博客：https://www.jianshu.com/p/9da2b1e7ef8e

spring boot 启动分析参考博客：https://www.jianshu.com/p/84f45bea357c

开源框架是如何通过JMX来做监控的(一) - JMX简介和Standard MBean: https://www.cnblogs.com/trust-freedom/p/6842332.html

JMX超详细解读: https://www.cnblogs.com/dongguacai/p/5900507.html

spring boot admin: https://juejin.cn/post/6844903984109617165

spring boot @Import 注解： https://www.cnblogs.com/imyjy/p/16092825.html

HttpClient详细梳理: https://www.jianshu.com/p/14c005e9287c

Micrometer自定义业务监控指标: https://github.com/TFdream/blog/issues/340

springboot整合prometheus: https://sb-woms.gitbook.io/sb/prometheus/springboot-prometheus-03

springboot 中获取所有的api 接口: https://baeldung-cn.com/spring-boot-get-all-endpoints
  https://blog.csdn.net/anshichuxuezhe/article/details/115860852
  在模块 demo-43-web-redirect-01 中有一个 AllUrlsRunner


华为机试题库：https://www.nowcoder.com/ta/huawei/

webclient 配置： https://www.hangge.com/blog/cache/detail_2640.html

@ResponseBody不一定返回json: https://www.jianshu.com/p/c24d526d13c2

参考类：响应处理：`RequestResponseBodyMethodProcessor` 请求处理：`HttpMessageConverterExtractor`

```java
    // consumes 指定接收的请求头content-type 的类型，可以使用"!" 来做逻辑非运算。
    // produces 指定响应的请求头content-type 的类型
    @RequestMapping(method = RequestMethod.POST, 
            consumes = "!" + MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String temp() {
        return "OK";
    }
```

另外，还可以通过实现接口`WebMvcConfigurer` 来添加一些默认配置项。
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(@NonNull ContentNegotiationConfigurer configurer) {
        // 配置默认的响应体类型，需要与请求头的Accept 匹配才可以，同时还需要有对应的解析器（即：需要将数据转换成JSON 的转换实现类，参考：HttpMessageConverter）。
        // 这里的配置优先级低于：@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}
```


websocket: https://blog.csdn.net/yingxiake/article/details/51213060

https://zh.portaldacalheta.pt/using-spring-boot-websocket-implementation-with-stomp

http://www.gxitsky.com/article/1605453579451554

翻译的中文文档：https://potoyang.gitbook.io/spring-in-action-v4/untitled-11

消息转换器：

ByteArrayMessageConverter  实现 MIME 类型为 “application/octetstream” 的消息与 byte[] 之间的相互转换

MappingJackson2MessageConverter  实现 MIME 类型为 “application/json” 的消息与 Java 对象之间的相互转换

StringMessageConverter  实现 MIME 类型为 “text/plain” 的消息与 String 之间的相互转换

mini k8s: https://kubernetes.io/zh/docs/tutorials/hello-minikube/

k8s 教程：https://www.jianshu.com/p/ef400bfea973

k8s pod 与node 的区别说明：https://blog.csdn.net/zy103118/article/details/117964731

supertoken: https://supertokens.com

jwt token: https://blog.51cto.com/u_15127630/3892206
