







```txt
spring security 的默认用户名是：user
密码在启动的时候会在日志里面看得到。
```



> spring security 本质是过滤器链

1. `FilterSecurityInterceptor`

   ```txt
   是一个方法级的权限过滤器，基本位于过滤链的最底部
   ```

2. `ExceptionTranslationFilter`

   ```txt
   异常过滤器，是个异常过滤器，用来处理在认证授权过程中抛出的异常
   ```

3. `UsernamePasswordAuthenticationFilter`

   ```txt
   对/login 的post 请求做拦截，校验表单中用户名，密码。
   ```

4. `其他`



## spring security 加载过程

> 使用`SpringSecurity` 配置过滤器

`DelegatingFilterProxy`

```txt
在  doFilter() 中调用 了 initDelegate() 方法
在initDelegate() 方法中会获取到 FilterChainProxy (内置)
```

```java
  protected Filter initDelegate(WebApplicationContext wac) throws ServletException {
		String targetBeanName = getTargetBeanName();
		Assert.state(targetBeanName != null, "No target bean name set");
		Filter delegate = wac.getBean(targetBeanName, Filter.class);
		if (isTargetFilterLifecycle()) {
			delegate.init(getFilterConfig());
		}
		return delegate;
	}
```



`FilterChainProxy`

> 在下面得到过滤器链

```java
	private List<Filter> getFilters(HttpServletRequest request) {
		for (SecurityFilterChain chain : filterChains) {
			if (chain.matches(request)) {
				return chain.getFilters();
			}
		}

		return null;
	}
```





## 自定义实现用户名和密码的校验

1. 继承类`UsernamePasswordAuthenticationFilter`
2. 创建 类实现接口`UserDetailsService`，实现查询数据库过程，返回User 对象，这个User对象是安全框架 提供 的对象

### 步骤

```txt
第一步：创建配置类，设置使用哪个userDetailsService 实现类
第二步：编写实现类，返回User对象，User 对象有用户名密码和操作权限
```



### 两个重要接口

### `UserDetailsService`

```txt
如果是自己查数据库来得到用户名和密码，则需要实现当前接口
```

### `PasswordEncoder`

```txt
数据加密接口，用于返回User 对象里面密码加密。
```

```java
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        // 对密码进行加密
        String ciphertext = b.encode("suyunhong");
        // 打印加密之后的数据
        System.out.println(ciphertext);

        // 判断原字符串加密后和加密之前是否匹配
        boolean result = b.matches("suyunhong", ciphertext);
        System.out.println(result);
```



问题：

```txt
There is no PasswordEncoder mapped for the id "null"
```



## 跳过认证配置

```properties
```



