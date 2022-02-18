package com.suyh.es3201;

import com.suyh.es3201.pojo.User;
import com.suyh.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@ExtendWith(SpringExtension.class)
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

    // 获取索引，索引只能判断其是否存在
    @Test
    public void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("kuang_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        log.info("exists: {}", exists);
    }

    // 删除索引
    @Test
    public void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("kuang_index");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        log.info("delete result: {}", response.isAcknowledged());
    }

    // 添加文档
    @Test
    public void testAddDocument() throws IOException {
        User user = new User("狂神说", 3);
        IndexRequest request = new IndexRequest("kuang_index");

        // 规则 put /kuang_index/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        // 将数据放入请求 json
        request.source(JsonUtil.serializable(user), XContentType.JSON);

        // 发请求求
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        log.info(response.toString());
    }

    // 获取文档，判断是否存在 get /index/_doc/1
    @Test
    public void testIsExists() throws IOException {
        GetRequest request = new GetRequest("kuang_index", "1");
        // 不获取返回的 _source 的上下文数据
        request.fetchSourceContext(new FetchSourceContext(false));
        // 排序字段
        request.storedFields("_none_");

        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        log.info("result: {}", exists);
    }

    // 获取文档信息
    @Test
    public void testGetDoc() throws IOException, ParseException {
        GetRequest request = new GetRequest("kibana_sample_data_logs", "8Pe1-nUBGMFI76K-eiLF");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        Map<String, Object> source = response.getSource();
        Object timestamp = source.get("timestamp");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date timeDate = sdf.parse((String) timestamp);
        log.info("timeDate: {}", timeDate);
        log.info("timestamp: {}", timeDate.getTime());
        log.info("timestamp: {}", timestamp);
        log.info("result: {}", response.getSourceAsString());
        log.info("result: {}", response);   // 返回的全部内容和命令是一样的

        SimpleDateFormat sdfLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdfLocal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date localDate = sdfLocal.parse("2020-11-16 10:59:45.071");
        log.info("timeDate-local: {}", localDate);
        log.info("timeStamp-local: {}", localDate.getTime());
    }

    // 更新文档信息
    @Test
    public void testUpdateDoc() throws IOException {
        UpdateRequest request = new UpdateRequest("kuang_index", "1");
        request.timeout(TimeValue.timeValueSeconds(1));

        User user = new User("狂神说Java", 18);
        request.doc(JsonUtil.serializable(user), XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        log.info("result: {}", response.status());
        log.info("result: {}", response);   // 返回的全部内容和命令是一样的
    }

    // 删除文档记录
    @Test
    public void testDeleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest("kuang_index", "3");

        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        log.info("result:{}", response.status());
        log.info("result: {}", response);
    }

    @Test
    public void testBulkDoc() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(10));

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("kuangeshen1", 3));

        for (int i = 0; i < userList.size(); i++) {
            bulkRequest.add(new IndexRequest("kuange_index").id("" + (i + 1))
                    .source(JsonUtil.serializable(userList.get(i)), XContentType.JSON));
        }

        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("result: {}", response);
    }

    // 查询
    @Test
    public void testSearch() throws IOException {
        SearchRequest request = new SearchRequest("kuange_index");
        // 构建搜索条件
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "kuangeshen1");
        sourceBuilder.query(termQueryBuilder);
        // 分页查询
        sourceBuilder.from(0);
        sourceBuilder.size(10);

        // 将搜索条件放到查询对象中
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 得到搜索的结果，全部在hits 中
        SearchHits hits = response.getHits();
        log.info("hits: {}", hits);
        for (SearchHit documentField : hits) {
            log.info("documentField: {}", documentField.getSourceAsMap());
        }
    }

    @Test
    public void testSearchDateRange() throws IOException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String strFromDate = "2020-11-16T19:56:43.000Z";
        String strToDate = "2020-11-16T20:57:00.000Z";
        Date fromDate = sdf.parse(strFromDate);
        Date toDate = sdf.parse(strToDate);
        log.info("fromDate: {}", fromDate.getTime());
        log.info("toDate: {}", toDate.getTime());

        SearchRequest request = new SearchRequest("kibana_sample_data_logs");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        RangeQueryBuilder timestampRange = QueryBuilders.rangeQuery("timestamp");
        int rawOffset = TimeZone.getDefault().getRawOffset();
        timestampRange.from(fromDate.getTime() + rawOffset, true);
        timestampRange.to(toDate.getTime() + rawOffset, true);
//        timestampRange.timeZone("GMT+00:00");
//        timestampRange.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sourceBuilder.query(timestampRange);
        // 分页查询
        sourceBuilder.from(0);
        sourceBuilder.size(30);
        sourceBuilder.sort("timestamp", SortOrder.DESC);

        // 将搜索条件放到查询对象中
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 得到搜索的结果，全部在hits 中
        SearchHits hits = response.getHits();
        log.info("hits: {}", hits);
        log.info("hits total size: {}", hits.getTotalHits());
        for (SearchHit documentField : hits) {
            log.info("docId: {}", documentField.docId());
            log.info("id: {}", documentField.getId());
            log.info("documentField: {}", documentField.getSourceAsString());
            Object timestamp = documentField.getSourceAsMap().get("timestamp");
            String strTime = (String) timestamp;
            log.info("timestamp: {}", timestamp);
//            sdf.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
            Date parse = sdf.parse(strTime);
            log.info("parse: {}", parse.getTime());
        }
    }

    @Test
    public void testPutMapping() throws IOException {
        PutMappingRequest request = new PutMappingRequest("twitter");

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("message");
                {
                    builder.field("type", "text");
                }
                builder.endObject();
            }
            {
                builder.startObject("date");
                {
                    builder.field("type", "date");
                }
                builder.endObject();
            }
            {
                builder.startObject("age");
                {
                    builder.field("type", "long");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        request.source(builder);

        AcknowledgedResponse response = client.indices().putMapping(request, RequestOptions.DEFAULT);
        log.info("put mapping result: {}", response.isAcknowledged());
    }

    //  elasticsearch date 的默认类型："strict_date_optional_time||epoch_millis"


}
