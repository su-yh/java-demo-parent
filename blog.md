spring boot tomcat 源码分析参考博客：https://www.jianshu.com/p/9da2b1e7ef8e

spring boot 启动分析参考博客：https://www.jianshu.com/p/84f45bea357c

spring boot 源码博客： https://blog.csdn.net/woshilijiuyi/article/details/82219585

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

spring mvc 源码解析：
1. https://zhuanlan.zhihu.com/p/58354248
2. https://www.cnblogs.com/fangjian0423/p/springMVC-directory-summary.html

腾讯北极星：
https://polarismesh.cn/zh/doc/%E6%9E%B6%E6%9E%84%E5%8E%9F%E7%90%86/%E5%8C%97%E6%9E%81%E6%98%9F%E6%9C%8D%E5%8A%A1%E6%B2%BB%E7%90%86/%E5%9F%BA%E6%9C%AC%E5%8E%9F%E7%90%86.html#%E5%9F%BA%E6%9C%AC%E5%8E%9F%E7%90%86


java agent: 在main 方法运行前的处理

skywakling: https://github.com/apache/skywalking-java

skywalking学习视频：https://www.bilibili.com/video/BV1dy4y1V7ck/?p=7&spm_id_from=pageDriver&vd_source=aeeb0d76d779ad41a6bb47f7e362ee98

skywalking 中文文档(古老)：https://github.com/SkyAPM/document-cn-translation-of-skywalking

字节码修改技术: bytebuddy

SOFABoot  SOFAArk   https://www.sofastack.tech/projects/sofa-boot/sofa-ark-readme/

定义类加载模型，运行时底层插件、业务应用(模块)之间均相互隔离，单一插件和应用(模块)由不同的 ClassLoader 加载，可以有效避免相互之间的包冲突，提升插件和模块功能复用能力；
Java隔离容器之sofa-ark使用说明及源码解析: https://juejin.cn/post/6844903653828984845

业务系统模块化之模块化隔离方案：https://www.sofastack.tech/blog/sofastack-modular-isolation/

微服务治理技术白皮书: https://developer.aliyun.com/ebook/7565?spm=opensergo-website.topbar.0.0.0

higress:  https://higress.io/zh-cn/

WSL  windows 上运行linux 系统： https://learn.microsoft.com/zh-cn/windows/wsl/install

ServiceMesh的关键：边车模式（sidecar）： https://zhuanlan.zhihu.com/p/258527216

Istio 
Envoy 

正则表达式图形表示 工具： https://regexper.com/

POST MAN ：https://warped-crescent-146654.postman.co


sentinel 的规则配置同步功能借助nacos 来实现。
但是nacos 是有状态服务，且性能受资源的影响，在实际的使用过程中也遇到了一些问题，进而影响业务的正常功能。
所以我们对该功能进行自行实现，借鉴nacos 与apollo 的长轮询思路实现了arwen 服务，来实现所有sentinel client 实例的规则配置的实时同步能力。
这里面最大的问题就是缓存问题，如果同一个集群的多个sentinel client 被分流到了不同的arwen 那么如果使用本地缓存的话，那么所有的arwen 实例都将缓存所有的集群规则配置，这显示是不现实的。
所以最开始的时候是通过请求头hash 的方式来将相同集群的所有实例分流到同一个arwen 服务，这样相同sentinel client 集群的规则配置将只会存在于一台arwen 服务上面。
后来引入了redis，然后我们就将本地缓存取消然后使用redis 缓存能力。
但是这样也引起了另一个问题，那就是初始化时redis 中没数据。与界面修改后，redis 修改了，要同步到arwen中。这里最后采用的redis 事件通知能力来实现的。


ByteBuddy（史上最全）   https://blog.csdn.net/crazymakercircle/article/details/126579528
