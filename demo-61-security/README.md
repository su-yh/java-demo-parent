







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
   方法：`attemptAuthentication(..)`
   ```

4. `其他`



## spring security 加载过程

> 使用`SpringSecurity` 配置过滤器

`DelegatingFilterProxy`

```txt
在  doFilter() 中调用 了 initDelegate() 方法
在initDelegate() 方法中会获取到 FilterChainProxy (内置bean 对象)
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
该接口的主要功能就是加载对应的登录用户明细信息，在这里并不作身份验证。
如果您需要自定义身份验证过程，直接实现AuthenticationProvider会更有意义。

在登录时，会进入到UsernamePasswordAuthenticationFilter 取到用户名和密码，在最后调用this.getAuthenticationManager().authenticate(authRequest) 

该调用最终就会去 loadUserByUsername(..)   但是要注意，这个方法返回的密码是通过  PasswordEncoder 接口加密过的。
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



### LogoutSuccessHandler

> 用户登出成功时会调用，可以做相关缓存的清理工作。



问题：

```txt
There is no PasswordEncoder mapped for the id "null"
```



## 认证授权注解使用

1. `@Secured`

   ```txt
   用户具有某个角色，可以访问方法
   1. 开启注解功能 @EnableGlobalMethodSecurity(securedEnabled = true)
   2. 在方法上面添加注解 @Secured({"ROLE_sale"})
   ```

2. `@PreAuthorize`

```txt
在方法之前校验
1. 开启注解功能 @EnableGlobalMethodSecurity(prePostEnabled = true)
2. 在方法上面添加注解 @PreAuthorize("hasAnyAuthority('admins')")
```

```java
// 取到参数上面的对象，使用@P 注解
// authentication 允许直接访问从 SecurityContext获取的当前身份验证对象
@PreAuthorize("#c.name == authentication.name")
public void doSomething(@P("c") Contact contact);

// 在EL 表达式中使用常量
@PreAuthorize("@ss.hasDataPermissionRole(T(com.ads.common.enums.PermissionRoleEnums).ADMIN)")
```



3. `@PostAuthorize("hasAnyAuthority('admins')")`

   ```txt
   在方法之后校验，用的地方很少
   1. 开启注解功能 @EnableGlobalMethodSecurity(prePostEnabled = true)
   2. 在方法上面添加注解 @PostAuthorize("hasAnyAuthority('admins')")
   ```

   

4. `@PostFilter` `@PreFilter`

   ```txt
   @PostFilter  对方法的返回数据进行过滤
   @PreFilter   对传入方法数据进行过滤
   ```

   

## 相关的类

### SecurityContextHolder

> SecurityContextHolder.getContext().setAuthentication(..);
>
> SecurityContextHolder.getContext().getAuthentication();
>
> 获取当前登录用户，如果没有获取到，则spring security 则会认为用户未登录。

### WebSecurityConfigurerAdapter

### UsernamePasswordAuthenticationFilter

> 只有登录接口API 才会走该过滤器，否则将直接跳过。
>
> 在进行配置(`WebSecurityConfigurerAdapter`)的时候，该过滤器被创建出来。`HttpSecurity#formLogin()`

### AuthenticationManager

### SecurityContextPersistenceFilter

> SecurityContextHolder.getContext().getAuthentication();
>
> SecurityContextHolder.getContext().setAuthentication(..);
>
> 上下文对象，用来存储当前登录的用户信息，当对象存在则判断为已认证用户，否则为未认证用户。
>
> 同时会在请求结束时清理。

### SecurityContextImpl

### ThreadLocalSecurityContextHolderStrategy

### LoginUrlAuthenticationEntryPoint

> 默认的认证失败的后置处理器

### SavedRequestAwareAuthenticationSuccessHandler

> 默认的认证成功的后置处理器

### **AuthenticationProvider** 

> 认处理供器，实现认证逻辑。
>
> 首先判断进入到该流的 authentication 对象是不是当前provider 所支持的认证处理器。
>
> supports(Class<?>..)
>
> 认证处理器的默认处理类是 DaoAuthenticationProvider，因为它支持UsernamePasswordAuthenticationToken 类的处理。
>
> 并且在ProviderManager 中初始化了  DaoAuthenticationProvider  的一个对象在处理器列表中。

### DaoAuthenticationProvider  

### ProviderManager

> 认证提供器管理类
>
> 它管理着所有的认证提供器，并且按存在的提供器列表按照顺序依次调用，直到有某一个提供器能够支持处理该token(authentication) 时就交由它处理。
>
> 同时在处理结束后的返回对象不为null，则结束认证。

### AuthenticationManagerBuilder

> 构建ProviderManager

### BasicAuthenticationFilter