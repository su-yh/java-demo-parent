

## `maven-release-plugin` 插件
参考博客：https://www.jianshu.com/p/c4c2ae1686a2

https://blog.csdn.net/shenchaohao12321/article/details/79302791

https://www.cnblogs.com/cowboys/p/10400784.html


```xml
        <!-- provided: 该依赖只在这里使用，子项目可以不使用该依赖中的任何类 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <scope>provided</scope>
        </dependency>
```

```xml
        <!-- compile + true: 子类中如果没有添加这个依赖则会报某个类不存在异常。 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <scope>compile</scope>
            <optional>true</optional>
        </dependency>
```
