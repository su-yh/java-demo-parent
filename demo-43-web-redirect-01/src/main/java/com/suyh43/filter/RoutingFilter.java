package com.suyh43.filter;


import javax.servlet.AsyncContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoutingFilter implements Filter {
    private RouteDelegate routeDelegate;
    private AsyncRouteDelegate asyncRouteDelegate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = RouteUtils.makeRedirectUrl("127.0.0.1:8080", "${contextPath/apiPath}", request.getQueryString());
        if (request.isAsyncSupported()) {
            AsyncContext asyncContext = request.startAsync();
            asyncContext.start(() -> asyncRouteDelegate.routeRedirect(url, request, response, asyncContext::complete));
        } else {
            routeDelegate.routeRedirect(url, request, response);
        }
    }
}
