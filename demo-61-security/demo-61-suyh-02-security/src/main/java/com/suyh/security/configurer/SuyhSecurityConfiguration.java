package com.suyh.security.configurer;

import com.suyh.security.filter.JwtAuthenticationTokenFilter;
import com.suyh.security.filter.SmsCodeAuthenticationProcessingFilter;
import com.suyh.security.handler.AuthenticationAfterForwardHandler;
import com.suyh.security.provider.SmsCodeAuthenticationProvider;
import com.suyh.security.service.SuyhUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author suyh
 * @since 2023-11-04
 */
@Configuration
@RequiredArgsConstructor
public class SuyhSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final SuyhUserDetailsService userDetailsService;

    // 如果没有当前这个bean 对象，则会报如下错误：
    // There is no PasswordEncoder mapped for the id "null"
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 使用登录成功/失败的后置处理URL 则这里就可以不用处理了，这些都是spring security 内置的功能，我们不需要用到这些了，
        // 直接在成功/失败的后置处理的URL 里面抛出认证失败的业务异常就可以了。
        // 401 认证失败的处理
        // suyh - 像ruoyi 和芋道的实现都是直接响应结果并指定401 ，但是我觉得可以在统一的异常处理，那里再解决。
        // 不行，这个异常已经处理处理，不会走到统一的异常处理那里去了，所以这里必须要处理。
        // 不过，现在使用了认证成功/失败的处理器，都是直接内部forward 到一个url，所以所有的异常处理都可以在该url 里面再抛出就可以了。
        // http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl());

        // 403 权限不足的异常处理
        // suyh - 像ruoyi 和芋道的实现都是直接响应结果并指定403 ，但是我觉得可以在统一的异常处理，那里再解决。
        // 不过，现在使用了认证成功/失败的处理器，都是直接内部forward 到一个url，所以所有的异常处理都可以在该url 里面再抛出就可以了。
        // http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl());

        // 基于token 处理，所以禁用session.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 基于用户名/密码的登录校验处理器
        AuthenticationAfterForwardHandler usernamePasswordForwardHandler = new AuthenticationAfterForwardHandler(
                "/login/after/successful/username/password", "/login/after/failure/username/password");
        http.formLogin()    // 登录页面，这里会引入 UsernamePasswordAuthenticationFilter
                .loginProcessingUrl("/user/login") // 登录访问路径，这个路径并不需要我们自己实现，security 它会自动处理。
                // 当登录成功之后的自定义处理器，默认实现是：SavedRequestAwareAuthenticationSuccessHandler
                .successHandler(usernamePasswordForwardHandler)
                .failureHandler(usernamePasswordForwardHandler);

        // 基于短信的登录校验处理器
        // 可以添加多组登录处理器与相对应的过滤器
        AuthenticationAfterForwardHandler smsCodeForwardHandler = new AuthenticationAfterForwardHandler(
                "/login/after/successful/sms/code", "/login/after/failure/sms/code");
        // 这里的主要作用就是将spring security 中的一些公共配置也作用于下面的认证器与过滤器
        // 其中最重要的一个配置就是 ProviderManager 对象，如果没有这个apply() 方法的调用，则 filter 中将不会有值。
        SmsCodeAuthenticationProcessingFilter filter = new SmsCodeAuthenticationProcessingFilter("/sms/login");
        // 这里的调用等同于 http.formLogin()
        http.apply(new SmsFormLoginConfigurer<>(filter)).loginProcessingUrl("/sms/login")
                .successHandler(smsCodeForwardHandler)
                .failureHandler(smsCodeForwardHandler);
        http.authenticationProvider(new SmsCodeAuthenticationProvider());
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

//        http.formLogin()
//                .loginPage("/login.html") // 登录页面设置
//                // 登录成功之后，内部跳转路径，该实现依赖于默认的成功处理器(SavedRequestAwareAuthenticationSuccessHandler)
//                .defaultSuccessUrl("/login/after/successful")
//                // .failureUrl(..)  // 该重定向则是以302 的方式进行。
//                // 密码错误时，会内部跳转到该uri 中。不过该实现是依赖于默认的实现。
//                .failureForwardUrl("/login/after/failure");

        http.authorizeRequests().antMatchers("/", "/test/hello").permitAll() // 设置哪些路径可以直接访问，不需要认证
                // .antMatchers("/test/index").hasAuthority("admins")  // 指定路径 /test/index 需要 admins 权限才可以访问，该admins 对应SuyhUserDetailsService  中的权限
                // .antMatchers("/test/index").hasAnyAuthority("admins,manager")  // 指定路径 /test/index 需要 admins或者manager 权限才可以访问，该admins 对应SuyhUserDetailsService  中的权限
                // .antMatchers("/test/index").hasRole("sale") // 这里只写了sale, 但是它实际上被处理成了: ROLE_sale
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and().csrf().disable();   // 关闭csrf 防护

        http.addFilterBefore(new JwtAuthenticationTokenFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);

        http.logout().logoutUrl("/logout").logoutSuccessHandler(userDetailsService);
    }
}
