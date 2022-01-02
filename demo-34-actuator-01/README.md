参考博客：
    基础介绍：http://www.ityouknow.com/springboot/2018/02/06/spring-boot-actuator.html
    自定义端点：https://bbs.huaweicloud.com/blogs/detail/194157
    安全：https://segmentfault.com/a/1190000021611510
    
    健康检查的实现类：HealthEndpoint

所有的统计数据都会在应用启动的时候注册到统计注册类中：`io.micrometer.core.instrument.MeterRegistry#getOrCreateMeter`

JvmGcMetrics

TomcatMetrics

spring boot actuator: https://www.hangge.com/blog/cache/detail_2723.html

注册度量：DataSourcePoolMetrics

其他：

CommonsDbcp2DataSourcePoolMetadata、DataSourcePoolMetadataProvidersConfiguration

使用：@Import(DataSourcePoolMetadataProvidersConfiguration.class)

Tomcat 线程池可以通过`beanServer.queryNames(getNamePattern(":type=ThreadPool,name=*"), null);`的方式获取到相关信息。
是因为下面的这部分代码。
io.micrometer.core.instrument.binder.tomcat.TomcatMetrics#registerThreadPoolMetrics
但是数据源的没找到，注册到`MeterRegistry`的是如下代码，似乎并没有往`MBeanServer`中注册。需要好好看一下。
org.springframework.boot.actuate.metrics.jdbc.DataSourcePoolMetrics#bindTo
    ```java
        // 这里最重要的就是：DataSourcePoolMetadata::getActive，就是需要实现该接口，然后就可以拿到对应的值了。
        // 对于我们来说，需要实现druid 数据源连接池的话，则可以按org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata 
        // 模仿着来实现。
        bindPoolMetadata(registry, "active",
                "Current number of active connections that have been allocated from the data source.",
                DataSourcePoolMetadata::getActive);    
    ```
org.springframework.boot.actuate.metrics.jdbc.DataSourcePoolMetrics#bindDataSource

结论：
这两个注册都是将统计数据注册到`MeterRegistry`中，可以通过`MeterRegistry` 来获取到这些数据的统计信息。
上面的Tomcat 线程池，的统计数据来自：`MBeanServer`，而下面的那个数据源的统计数据来自：`DataSource`。

这里面最关系的技术是`MBeanServer` 与`MeterRegistry` 的使用。

---

- tag 参数的使用

  tag 参数的使用是以 `key:value` 的形式在请求参数上面使用的。

查看度量信息，数据源连接池： http://localhost:6557/gov/app/demo/actuator/metrics/jdbc.connections.max

```json
{
    "name": "jdbc.connections.max",
    "description": "Maximum number of active connections that can be allocated at the same time.",
    "baseUnit": null,
    "measurements": [
        {
            "statistic": "VALUE",
            "value": 61
        }
    ],
    "availableTags": [
        {
            "tag": "name",
            "values": [
                "dataSourceSlave",
                "dataSourceMaster"
            ]
        }
    ]
}
```

查看对应的数据源连接池数据信息： http://localhost:6557/gov/app/demo/actuator/metrics/jdbc.connections.max?tag=name:dataSourceSlave

```json
{
    "name": "jdbc.connections.max",
    "description": "Maximum number of active connections that can be allocated at the same time.",
    "baseUnit": null,
    "measurements": [
        {
            "statistic": "VALUE",
            "value": 30
        }
    ],
    "availableTags": []
}
```

