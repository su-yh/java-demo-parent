package com.suyh.security.filter;

import com.suyh.security.vo.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author suyh
 * @since 2024-03-09
 */
public class SmsCodeAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    // 表单请求参数名
    public static final String FORM_MOBILE_KEY = "mobile";
    public static final String FORM_SMS_CODE_KEY = "smsCode";

    public SmsCodeAuthenticationProcessingFilter(String url) {
        super(new AntPathRequestMatcher(url, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String mobile = obtainMobile(request);
        mobile = (mobile != null) ? mobile : "";
        mobile = mobile.trim();
        String smsCode = obtainSmsCode(request);
        smsCode = (smsCode != null) ? smsCode : "";
        // suyh - 这里也是关键，因为下面的认证处理器是根据类名来匹配是否支持该处理，所以类必须要匹配才行。
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile, smsCode);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(FORM_MOBILE_KEY);
    }

    private String obtainSmsCode(HttpServletRequest request) {
        return request.getParameter(FORM_SMS_CODE_KEY);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
