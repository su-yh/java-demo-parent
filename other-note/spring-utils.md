
spring 提供的方便使用的工具类

反射相关: `ReflectionUtils`

方法相关：`MethodIntrospector`，大多数情况用它来查找带注解的方法。某个类里面，查找带了某个注解的方法集。

serevlet 的工具类：ServletRequestPathUtils   如：ServletRequestPathUtils.getParsedRequestPath(request)

AnnotatedElementUtils

数据大小类，在配置文件中使用比较方便：org.springframework.util.unit.DataSize

并且它还提供了，数字类型的转换和字符串类型的转换：`org.springframework.boot.convert.NumberToDataSizeConverter`   `org.springframework.boot.convert.StringToDataSizeConverter`

利用反射实例化一个对象: `BeanUtils#instantiateClass`


DataSize 可以用来解析表示文件大小的类，比如配置字节大小的字符串使用：KB、MB、GB等。


org.springframework.core.io.support.SpringFactoriesLoader.loadFactoryNames
SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader())

