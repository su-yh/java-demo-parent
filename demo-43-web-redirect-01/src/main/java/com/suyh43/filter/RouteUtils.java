package com.suyh43.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class RouteUtils {
    public static String makeRedirectUrl(
            @NonNull String routeAddress, @NonNull String routeUri, String queryString) {
        if (!routeAddress.startsWith("http://") && !routeAddress.startsWith("https://")) {
            routeAddress = "http://" + routeAddress;
        }
        if (queryString == null || queryString.trim().isEmpty()) {
            queryString = "";
        } else {
            queryString = "?" + queryString.trim();
        }
        return routeAddress + routeUri + queryString;
    }

    /**
     * 组装RequestEntity
     *
     * @param request request
     * @param url     url
     * @return request entity
     * @throws URISyntaxException exception
     * @throws IOException        exception
     */
    public static RequestEntity<byte[]> createRequestEntity(
            @NonNull HttpServletRequest request, @NonNull String url, MultiValueMap<String, String> defaultHeaders)
            throws URISyntaxException, IOException {
        String method = request.getMethod();
        HttpMethod httpMethod = HttpMethod.resolve(method);
        HttpHeaders headers = parseRequestHeader(request, defaultHeaders);
        byte[] body = parseRequestBody(request);
        return new RequestEntity<>(body, headers, httpMethod, new URI(url));
    }

    /**
     * 组装请求body
     *
     * @param request request
     * @return body
     * @throws IOException io exception
     */
    public static byte[] parseRequestBody(@NonNull ServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }

    /**
     * 组装所有头信息
     *
     * @param request request
     * @return headers
     */
    public static HttpHeaders parseRequestHeader(
            @NonNull HttpServletRequest request, MultiValueMap<String, String> defaultHeaders) {
        HttpHeaders headers = defaultHeaders == null ? new HttpHeaders() : new HttpHeaders(defaultHeaders);
        List<String> headerNames = Collections.list(request.getHeaderNames());
        for (String headerName : headerNames) {
            Enumeration<String> hds = request.getHeaders(headerName);
            while (hds.hasMoreElements()) {
                String headerValue = hds.nextElement();
                if (StringUtils.hasText(headerValue)) {
                    headers.set(headerName, headerValue);
                    break;
                }
            }
        }

        return headers;
    }
}
