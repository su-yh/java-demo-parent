package com.suyh.security.service;

import com.suyh.security.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author suyh
 * @since 2023-11-04
 */
@Service("userDetailsService")
public class SuyhUserDetailsService implements UserDetailsService, LogoutSuccessHandler, UserToken {
    private static final String secret = "abcdefghijklmnopqrstuvwxyz";

    // TODO: suyh - 缓存用户信息

    // 加载用户信息，主要是在用户登录的时候才会调用，在使用过程中基本都是通过token 来获取登录用户详细信息。
    // 所以这里每次从数据库中查询影响也不大
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 这个用户所拥有哪些权限
        // admins 是权限，ROLE_sale  是角色名称，ROLE_ 是源代码中要求的前缀
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bCryptPasswordEncoder.encode("123");

        return new User("mary", pwd, auths);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // TODO: suyh - 用户登出，需要清理相关缓存以及token 失效
    }

    @Override
    public User loginUserDetail(String token) {
        // 解析token 得到用户
        Claims claims = parseToken(token);
        String username = claims.getSubject();

        return new User(username, "123", AuthorityUtils.NO_AUTHORITIES);
    }

    public static String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
