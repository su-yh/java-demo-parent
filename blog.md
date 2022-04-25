spring boot tomcat 源码分析参考博客：https://www.jianshu.com/p/9da2b1e7ef8e

spring boot 启动分析参考博客：https://www.jianshu.com/p/84f45bea357c

开源框架是如何通过JMX来做监控的(一) - JMX简介和Standard MBean: https://www.cnblogs.com/trust-freedom/p/6842332.html

JMX超详细解读: https://www.cnblogs.com/dongguacai/p/5900507.html

spring boot admin: https://juejin.cn/post/6844903984109617165


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
