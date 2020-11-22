# demo-33-jackson-01


对于web 项目的`@RequestBody`以及`@ResponseBody`进行json序列化与反序列化时所使用的json参数都是默认的，如果想要对其进行自定义统一配置的话要怎么做呢？

这里就做一个demo。

这里使用使用注入ObjectMapper 的方式进行配置