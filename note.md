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
2. 
