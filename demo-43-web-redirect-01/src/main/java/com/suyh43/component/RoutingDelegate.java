package com.suyh43.component;

import com.suyh43.filter.RoutingFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

/**
 * 路由转发代理
 * 有跨域问题
 *
 * @deprecated 参考: {@link RoutingFilter}
 */
@Component
@Deprecated
@Slf4j
public class RoutingDelegate {
    /**
     * 请求转发
     *
     * @param request request
     * @param addr    示例：http://localhost:9571
     * @param uri     示例: /app/briefinfos.json 必须以/ 开头
     * @return result
     */
    public ResponseEntity<String> redirect(HttpServletRequest request, String addr, String uri) {
        try {
            // build up the redirect URL
            String redirectUrl = createRedirectUrl(request, addr, uri);
            RequestEntity<byte[]> requestEntity = createRequestEntity(request, redirectUrl);
            return route(requestEntity);
        } catch (URISyntaxException | IOException ex) {
            log.error("redirect failed", ex);
            return new ResponseEntity<>("redirect failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String createRedirectUrl(HttpServletRequest request, String addr, String uri) {
        String queryString = request.getQueryString();
        if (queryString == null || queryString.trim().isEmpty()) {
            queryString = "";
        } else {
            queryString = "?" + queryString;
        }

        if (!addr.startsWith("http://") && !addr.startsWith("https://")) {
            addr = "http://" + addr;
        }

        return addr + uri + queryString;
    }

    /**
     * 组装RequestEntity
     *
     * @param request request
     * @param url url
     * @return request entity
     * @throws URISyntaxException exception
     * @throws IOException exception
     */
    private RequestEntity<byte[]> createRequestEntity(HttpServletRequest request, String url) throws URISyntaxException, IOException {
        String method = request.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(method);
        MultiValueMap<String, String> headers = parseRequestHeader(request);
        byte[] body = parseRequestBody(request);
        return new RequestEntity<>(body, headers, httpMethod, new URI(url));
    }

    /**
     * 执行请求转发
     *
     * @param requestEntity 组装 好的所有的请求数据
     * @return result
     */
    private ResponseEntity<String> route(RequestEntity<byte[]> requestEntity) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(requestEntity, String.class);
    }

    /**
     * 组装请求body
     *
     * @param request request
     * @return body
     * @throws IOException io exception
     */
    private byte[] parseRequestBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }

    /**
     * 组装所有头信息
     *
     * @param request request
     * @return headers
     */
    private MultiValueMap<String, String> parseRequestHeader(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            List<String> headerValues = Collections.list(request.getHeaders(headerName));
            for (String headerValue : headerValues) {
                headers.add(headerName, headerValue);
            }
        }
        return headers;
    }
}
