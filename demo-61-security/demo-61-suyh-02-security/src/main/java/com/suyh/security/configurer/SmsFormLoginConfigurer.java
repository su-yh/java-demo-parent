package com.suyh.security.configurer;

import com.suyh.security.filter.SmsCodeAuthenticationProcessingFilter;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author suyh
 * @since 2024-03-09
 */
public class SmsFormLoginConfigurer<H extends HttpSecurityBuilder<H>>
        extends AbstractAuthenticationFilterConfigurer<H, SmsFormLoginConfigurer<H>, SmsCodeAuthenticationProcessingFilter> {

    public SmsFormLoginConfigurer(SmsCodeAuthenticationProcessingFilter filter) {
        super(filter, null);
        // 通过该方法就可以直接拿到filter 实例对象，然后可以给他做一些初始化操作。
        SmsCodeAuthenticationProcessingFilter authenticationFilter = getAuthenticationFilter();
        System.out.println(authenticationFilter.getClass().getName());
    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        // 参考 FormLoginConfigurer
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }
}
