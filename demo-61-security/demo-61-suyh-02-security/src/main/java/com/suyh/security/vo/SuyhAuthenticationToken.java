package com.suyh.security.vo;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.User;

/**
 * @author suyh
 * @since 2024-03-08
 */
public class SuyhAuthenticationToken extends AbstractAuthenticationToken {
    private final User user;
    public SuyhAuthenticationToken(User user) {
        // 权限一般不使用spring security 内置的。
        super(null);

        this.user = user;

        // 已经认证通过
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    // 这个一般是登录用户实体
    @Override
    public Object getPrincipal() {
        return user;
    }
}
