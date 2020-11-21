package com.suyh.es3201;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application3201.class)
@Slf4j
public class Application3201Tests {
    @Resource
    private RestHighLevelClient client;

    // 测试索引创建
    @Test
    public void testCreateIndex() throws IOException {
        // 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("kuang_index");
        // 执行创建命令
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        log.info(createIndexResponse.toString());
    }
}
