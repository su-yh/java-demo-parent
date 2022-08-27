该组件是基于spring boot 的扩展接口实现的，所以该组件的使用必须基于spring boot 项目
主要用于连接配置中心，启动的时候加载配置中心的配置项。

首先加载本地应用配置属性到spring 的ConfigurableEnvironment 中。
同时在ConfigurableEnvironment 中为配置中心的属性占位。
从配置中心拉取相关属性，并将其属性放到提前占好位上。

