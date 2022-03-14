package com.suyh43.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
public class RouteDelegate {
    private final RestTemplate restTemplate;

    public RouteDelegate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 路由转发
     *
     * @param redirectUrl 转发的目标url
     * @param request     request
     * @param response    response
     */
    public void routeRedirect(
            @NonNull String redirectUrl,
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response) throws IOException {
        routeRedirect(redirectUrl, request, response, null);
    }

    public void routeRedirect(
            @NonNull String redirectUrl,
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            MultiValueMap<String, String> defaultHeaders) throws IOException {
        try {
            ResponseEntity<byte[]> responseEntity = redirect(request, redirectUrl, defaultHeaders);
            int statusCode = responseEntity.getStatusCode().value();
            HttpHeaders headers = responseEntity.getHeaders();
            byte[] body = responseEntity.getBody();
            response.setStatus(statusCode);
            headers.forEach((headerName, headerValues) -> {
                for (String headerValue : headerValues) {
                    response.setHeader(headerName, headerValue);
                }
            });

            if (body != null) {
                ServletOutputStream writer = response.getOutputStream();
                writer.write(body);
                writer.flush();
            }
        } catch (Exception exception) {
            log.error("do api route failed, arms runtime url: {}, msg: {}", redirectUrl, exception.getMessage());
            response.sendError(200501);
        }
    }

    private ResponseEntity<byte[]> redirect(
            @NonNull HttpServletRequest request, @NonNull String redirectUrl, MultiValueMap<String, String> defaultHeaders)
            throws URISyntaxException, IOException {
        RequestEntity<byte[]> requestEntity = RouteUtils.createRequestEntity(request, redirectUrl, defaultHeaders);
        return restTemplate.exchange(requestEntity, byte[].class);
    }
}
