
package com.suyh1804.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class DemoHttpClient {
    private CloseableHttpClient httpClient;

    @PostConstruct
    public void initHttpClient() {
        // 创建连接池管理器
        PoolingHttpClientConnectionManager connectionManager
                = new PoolingHttpClientConnectionManager(60, TimeUnit.SECONDS);
        connectionManager.setMaxTotal(1000);
        connectionManager.setDefaultMaxPerRoute(50);

        // 创建RequestConfig
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        requestConfigBuilder.setConnectTimeout(10000);
        requestConfigBuilder.setConnectionRequestTimeout(2000);
        RequestConfig requestConfig = requestConfigBuilder.build();

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        httpClientBuilder.setConnectionManager(connectionManager);
        httpClientBuilder.disableAutomaticRetries();
        httpClient = httpClientBuilder.build();
    }

    public boolean doHttpRequest() throws Exception {
        String host = "localhost";
        int port = 9571;
        String apiPath = "/registry/machine";

        try {
            HttpGet httpGet = makeHttpGet(host, port, apiPath);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            // 自动回收到连接池中
            EntityUtils.consume(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return true;
            }

            log.warn("[SimpleHttpHeartbeatSender] Failed to send heartbeat to {}:{}{}, http status code: {}",
                    host, port, apiPath, statusCode);
        } catch (Exception exc) {
            log.error("[SimpleHttpHeartbeatSender] Failed to send heartbeat to {}:{}{}, http status code: {}",
                    host, port, apiPath, exc);
        }
        return false;
    }

    private HttpGet makeHttpGet(String host, int port, String apiPath) throws URISyntaxException {
        String strUri = "http://" + host + ":" + port + apiPath;
        URIBuilder uriBuilder = new URIBuilder(strUri);
        uriBuilder.addParameter("ip", "10.58.88.159");
        uriBuilder.addParameter("port", "7081");
        uriBuilder.addParameter("app", "gov-app-demo-local");
        uriBuilder.addParameter("hostname", "DESKTOP-IGAVVDB");
        uriBuilder.addParameter("app_type", "0");
        uriBuilder.addParameter("v", "1.8.1");
        uriBuilder.addParameter("version", System.currentTimeMillis() + "");
        URI uri = uriBuilder.build();
        return new HttpGet(uri);
    }
}
