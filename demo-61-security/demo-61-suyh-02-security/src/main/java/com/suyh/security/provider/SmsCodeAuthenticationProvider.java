package com.suyh.security.provider;

import com.suyh.security.vo.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;

/**
 * 按照这样的处理来看，在这里处理并不是一个合适的方式，主要是还需要实现一个 filter。
 * 在filter 中需要判断登录 url，请求方式(POST)，同时还要取出所有需要参数，最终封装到 (SmsCodeAuthenticationToken) 对象中。
 * 另外还要处理其他的逻辑，比如帐号状态是否正常，帐是否被封禁等等。
 * 不过，如果的确是需要自己的一些逻辑，那么这样倒是也可以。
 * 还有一个就是认证成功/失败的后置处理。所以感觉整体来讲还是不要自己实现provider 更好。
 *
 * @author suyh
 * @since 2024-03-09
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        String mobile = authenticationToken.getMobile();
        String smsCode = authenticationToken.getSmsCode();
        // 接下来就是处理手机号与验证码的匹配。
        if (StringUtils.isEmpty(mobile)) {
            throw new BadCredentialsException("手机号不能为空");
        }
        if (StringUtils.isEmpty(smsCode)) {
            throw new BadCredentialsException("验证码不能为空");
        }

        // TODO: suyh - 校验逻辑
        // ...

        // 认证通过则返回一个非null 对象，认证未通过则返回一个null 对象即可，谁链会继续往下执行。
        // 参考：ProviderManager.authenticate
        // result = provider.authenticate(authentication);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (SmsCodeAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
