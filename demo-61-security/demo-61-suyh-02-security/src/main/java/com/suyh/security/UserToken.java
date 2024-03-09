package com.suyh.security;

import org.springframework.security.core.userdetails.User;

/**
 * @author suyh
 * @since 2024-03-08
 */
public interface UserToken {
    User loginUserDetail(String token);
}
