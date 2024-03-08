package com.suyh.security.vo;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author suyh
 * @since 2024-03-08
 */
public class SuyhAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;
    // TODO: suyh - 这里显然不应该只是一个简单的String 而应该是一个登录成功的用户相关信息。
    public SuyhAuthenticationToken(String username) {
        // 权限一般不使用spring security 内置的。
        super(null);

        this.principal = username;

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
        return principal;
    }
}
