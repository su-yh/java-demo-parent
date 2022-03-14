
package com.suyh43.controller;

import com.suyh43.component.RoutingDelegate;
import com.suyh43.filter.RoutingFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 请求转发到sentinel 服务的请求
 * @deprecated 参考：{@link RoutingFilter}
 */
@RestController
@RequestMapping("/redirect-service")
@Deprecated
@Slf4j
public class RedirectSentinelController {
    @Resource
    private RoutingDelegate routingDelegate;

    /**
     * 请求转发，将该请求全部转发到指定的一个服务上面对应的服务上
     *
     * @param request request
     * @param addr    addr: localhost:8080
     * @param uri     uri: /suyh/demo/name
     * @return result
     */
    @RequestMapping(value = "/any")
    public ResponseEntity<String> catchAll(
            HttpServletRequest request,
            @RequestHeader(value = "target-addr") String addr,
            @RequestHeader(value = "target-uri") String uri) {
        return routingDelegate.redirect(request, addr, uri);
    }
}
