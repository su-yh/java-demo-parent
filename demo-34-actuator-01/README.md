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

