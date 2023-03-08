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

