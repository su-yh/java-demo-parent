package com.suyh43.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

@Slf4j
public class AsyncRouteDelegate {
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
            @NonNull HttpServletResponse response,
            @NonNull Runnable runnable) {
        HttpMethod method = HttpMethod.resolve(request.getMethod());
        Map<String, String[]> parameterMap = request.getParameterMap();
        HttpHeaders httpRequestHeaders = RouteUtils.parseRequestHeader(request, null);
        WebClient webclient = WebClient.builder().baseUrl(redirectUrl).build();
        Mono<ResponseEntity<byte[]>> entityMono = webclient.method(method)
                .uri(uriBuilder -> uriBuilder.build(parameterMap))
                .httpRequest(clientHttpRequest -> {
                    HttpHeaders headers = clientHttpRequest.getHeaders();
                    headers.putAll(httpRequestHeaders);
                })
                .retrieve()
                .toEntity(byte[].class);

        entityMono.timeout(Duration.ofSeconds(2))
                .doFinally(signalType -> runnable.run())
                .doOnError(exception -> log.error("web client request failed.", exception))
                .subscribe(res -> {
                    // 转发响应结果
                    HttpHeaders headers = res.getHeaders();
                    headers.forEach((headerName, headerValues) -> {
                        for (String headerValue : headerValues) {
                            response.addHeader(headerName, headerValue);
                        }
                    });
                    byte[] body = res.getBody();
                    if (body != null) {
                        try {
                            ServletOutputStream outputStream = response.getOutputStream();
                            outputStream.write(body);
                            outputStream.flush();
                        } catch (IOException e) {
                            // TODO: suyh - 失败也需要给出响应。
                            e.printStackTrace();
                        }
                    }
                });
    }
}
