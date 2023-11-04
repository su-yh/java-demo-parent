package com.suyh6006.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * @author suyh
 * @since 2023-11-04
 */
public class SuyhSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置没有权限访问跳转自定义页面
        http.exceptionHandling().accessDeniedPage("/unauth.html");

        http.formLogin()    // 自定义自己编写 的登录页面
                .loginPage("/login.html") // 登录页面设置
                .loginProcessingUrl("/user/login") // 登录访问 路径，这个路径并不需要我们自己实现，security 它会自动处理。
                .defaultSuccessUrl("/test/index").permitAll()   // 登录成功之后，跳转路径
                .and().authorizeRequests().antMatchers("/", "/test/hello", "/user/login").permitAll() // 设置哪些路径可以直接访问，不需要认证
                // .antMatchers("/test/index").hasAuthority("admins")  // 指定路径 /test/index 需要 admins 权限才可以访问，该admins 对应SuyhUserDetailsService  中的权限
                // .antMatchers("/test/index").hasAnyAuthority("admins,manager")  // 指定路径 /test/index 需要 admins或者manager 权限才可以访问，该admins 对应SuyhUserDetailsService  中的权限
                .antMatchers("/test/index").hasRole("sale") // 这里只写了sale, 但是它实际上被处理成了: ROLE_sale

                .anyRequest().authenticated().and().csrf().disable();   // 关闭csrf 防护
    }
}
