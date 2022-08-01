







## jmeter -每10 分钟登录一次

1. 添加HTTP Cookie 管理器

   这个管理器里面不需要有任何东西，但是不能少

2. 添加用户定义的变量

   这里指定这个变量的名称与值：`expiredLoginTimestamp = 0`

   因为后面要用到所以直接固定了，当然是可以随便定义的，只要前后匹配一致即可。

3. 添加逻辑控制器

   首先需要有一个线程组

   在线程组中依次点击：`添加  --> 逻辑控制器  -->  如果(if) 控制器`

   在`Expression(must evaluate to true or false)` 中添加groovy 脚本：

   ```groovy
   // 判断逻辑判断，是否要进入这个逻辑控制器执行相应的后续逻辑操作
   ${__groovy(${expiredLoginTimestamp} <= ${__time(,)})}
   ```

4. 添加登录的http 请求

   在前面的逻辑控制器中依次点击：`添加  -->  取样器  -->  HTTP请求`

5. 添加逻辑10 分钟登录的过期处理

   在登录的http 请求中依次点击：`添加  --> 后置处理器  -->  BeanShell 后置处理程序`

   添加脚本：

   ```js
   // 得到当前时间戳
   long tempLong = Long.parseLong("${__time(,)}");
   
   // 过期时间为10 分钟后
   tempLong += 10 * 60 * 1000;
   log.info("tempLong: {}", tempLong);
   
   // 修改前面指定的用户变量的值为10 分钟后
   vars.put("expiredLoginTimestamp", tempLong + "");
   ```

6. 其他

