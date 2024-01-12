



spring boot 加载resource 目录下面的所有配置文件



application.yarm

application.properties



在spring boot 2.4 之前对应的实现入口类是：`ConfigFileApplicationListener`

不过它会在spring boot 3.0.0 被移除



代替它的是`ConfigDataEnvironmentPostProcessor`



`PropertiesPropertySourceLoader` 是来加载后缀"properties" 以及"xml" 的配置文件

`YamlPropertySourceLoader` 是来加载后缀"yml"以及"yaml" 的配置文件

```properties
# PropertySource Loaders
org.springframework.boot.env.PropertySourceLoader=\
org.springframework.boot.env.PropertiesPropertySourceLoader,\
org.springframework.boot.env.YamlPropertySourceLoader
```









