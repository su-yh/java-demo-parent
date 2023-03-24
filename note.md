`server.max-http-header-size` 设置过大会导致OOM

参考博客：https://blog.csdn.net/qq_31929931/article/details/115319113

误删git 分支，恢复：https://www.cnblogs.com/sxdcgaq8080/p/14793684.html


Apsara Clouder云计算专项技能认证：云服务器ECS入门：https://edu.aliyun.com/certification/cldc15?spm=a2c6h.12873639.article-detail.6.45416e9eyuGs7a

参考答案：https://www.cnblogs.com/yanlai/p/16531097.html



1. 服务治理核心业务的开发、维护、部署、上线等；
2. 客户支撑，全量工作的推进；
3. 可信指标维护，开源扫描、CTS扫描、流水线成功率等；
4. 新人引导，框架重构与优化；
5. 解决项目中遇到的疑难问题等；
6. 代码仓库管理，多环境维护与管理；


疑难问题：
1. @ConfigurationProperties 没有绑定到实际配置值的问题(原因就是有一个BeanFactoryPostProcessor 依赖了这个bean 对象，使用这个配置bean 对象提前创建，而配置的绑定实现是一个BeanPostProcessor)；
2. 框架实现问题，通过调试找出源代码逻辑问题以解决。(比如dev 环境不让连配置中心，spring.profiles.active 只允许 使用指定的几个值，以及Scanbackpakeage 将所有的注解都扫描，没法在业务层控制)
3. 在controller 的方法参数上添加注解以获取到当前登录用户的相关信息。

```bash
证书命令
# 生成证书
# -alias jalor_gateway  别名：jalor_gateway
# -keyalg RSA  RSA 加密算法
# -validity 1 有效期1天
# -keystore d:/ssl/truststore.jks   存放路径
# -storepass gateway   密码：gateway
    keytool -genkey -alias jalor_gateway -keyalg RSA -keysize 1024 -keypass gateway -validity 1 -keystore d:/ssl/truststore.jks -deststoretype pkcs12 -storepass gateway 
导入证书
    keytool -import -alias HWIT -keystore  d:/ssl/truststore.jks -file  d:/ssl/HWIT.crt   -storepass gateway
删除证书
    keytool -delete -alias HWIT -keystore "d:/ssl/truststore.jks" -storepass gateway
    keytool -delete -alias jalor_gateway -keystore "d:/ssl/truststore.jks" -storepass gateway
```


CustomValidatorBean   

https://blog.csdn.net/f641385712/article/details/97270786

https://www.cnblogs.com/yourbatman/p/11387438.html

https://cloud.tencent.com/developer/article/1497732

RequestMappingHandlerMapping:  https://blog.csdn.net/f641385712/article/details/89810020


// 取当前时间的UTC秒值
        Instant instant = Instant.now();
        final long epochSecond = instant.getEpochSecond();

// 由于两个long 进行位运算无法得到一个正确的值，所以将long 转换成两个int 分别进行位逻辑运算。
```java
        // 两个int 合并拼接成一个long
        final ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(high);
        buffer.putInt(lower);
        buffer.flip();
        long result = buffer.getLong();
```
```java
        // 一个Long 转换成一个byte[]
        final byte[] array = ByteBuffer.allocate(Long.BYTES).putLong(1L).array();

        // 一个byte[8] 转换成一个Long
        final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(array, 0, array.length);
        buffer.flip();
        buffer.getLong();
```

```java
        Instant instant = Instant.now();
        final long epochSecond = instant.getEpochSecond();
        int high = (int) epochSecond;
        // 9 bits 用来自增存放同一微服务的实例数上限
        int serviceSequence = 0XFF800000;
        // 23 bits 用来自增存放每秒产生的uuid 上限
        int localSequence = 0X4FFFFF;
        int lower = serviceSequence | localSequence;

        final ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(high);
        buffer.putInt(lower);
        buffer.flip();
        long uuidResult = buffer.getLong();
        System.out.printf("high: %#X\n", high);
        System.out.printf("lower: %#X\n", lower);
        System.out.printf("result: %#X\n", uuidResult);
```

多redis 数据源，参考：JedisConnectionConfiguration
同时禁用自动配置：spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
还需要禁用：spring.data.redis.repositories.enabled=false
