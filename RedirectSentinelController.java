
package com.huawei.it.gov.controller;

import com.huawei.it.gov.component.RoutingDelegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 请求转发到sentinel 服务的请求
 */
@RestController
@RequestMapping("/sentinel-service")
@Slf4j
public class RedirectSentinelController {
    @Resource
    private RoutingDelegate routingDelegate;

    /**
     * 请求转发，将该请求全部转发到sentinel 对应的服务上
     *
     * @param request request
     * @param addr    addr
     * @param uri     uri
     * @return result
     */
    @RequestMapping(value = "/any")
    public ResponseEntity<String> catchAll(
            HttpServletRequest request,
            @RequestHeader(value = "gov-sentinel-addr") String addr,
            @RequestHeader(value = "gov-sentinel-uri") String uri) {
        return routingDelegate.redirect(request, addr, uri);
    }
}
