SpringApplicationRunListener 监听多个运行状态方法，总结如下：

| 监听方法                                         | 阶段说明                                                     | 版本 |
| ------------------------------------------------ | ------------------------------------------------------------ | ---- |
| starting()                                       | Spring 应用刚启动                                            | 1.0  |
| environmentPrepared(ConfigurableEnvironment)     | ConfigurableEnvironment 准备妥当，允许将其调整               | 1.0  |
| contextPrepared(ConfigurableApplicationContext)  | ConfigurableApplicationContext 准备妥当，允许将其调整        | 1.0  |
| contextLoaded(ConfigurableApplicationContext)    | ConfigurableApplicationContext 已装载，但仍未启动            | 1.0  |
| started(ConfigurableApplicationContext)          | ConfigurableApplicationContext 已启动，此时 Spring Bean 已初始化完成 | 2.0  |
| running(ConfigurableApplicationContext)          | Spring 应用正在运行                                          | 2.0  |
| failed(ConfigurableApplicationContext,Throwable) | Spring 应用运行失败                                          | 2.0  |

EventPublishingRunListener 监听方法与 Spring Boot 事件对应关系：

| 监听方法                                         | Spring Boot 事件                    | 版本 |
| ------------------------------------------------ | ----------------------------------- | ---- |
| starting()                                       | ApplicationStartingEvent            | 1.5  |
| environmentPrepared(ConfigurableEnvironment)     | ApplicationEnvironmentPreparedEvent | 1.0  |
| contextPrepared(ConfigurableApplicationContext)  |                                     |      |
| contextLoaded(ConfigurableApplicationContext)    | ApplicationPreparedEvent            | 1.0  |
| started(ConfigurableApplicationContext)          | ApplicationStartedEvent             | 2.0  |
| running(ConfigurableApplicationContext)          | ApplicationReadyEvent               | 2.0  |
| failed(ConfigurableApplicationContext,Throwable) | ApplicationFailedEvent              | 1.0  |

