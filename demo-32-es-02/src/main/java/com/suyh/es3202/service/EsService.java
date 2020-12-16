package com.suyh.es3202.service;

import com.suyh.dto.PageInfoDto;
import com.suyh.es3202.entity.ProcessFormEsDo;
import com.suyh.es3202.repository.ProcessFormRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 苏雲弘
 * @since 2020-11-26
 */
@Service
@Slf4j
public class EsService {

    @Resource
    private ProcessFormRepository processFormRepository;

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    public List<ProcessFormEsDo> getEntityList() {
        Iterable<ProcessFormEsDo> entityList = processFormRepository.findAll();
        List<ProcessFormEsDo> resultList = new ArrayList<>();
        for (ProcessFormEsDo processFormEntity : entityList) {
            log.info("process form entity: {}", processFormEntity);
            resultList.add(processFormEntity);
        }

        return resultList;
    }

    public String addEntity() {
        Date nowTime = new Date();

        String id = UUID.randomUUID().toString();

        ProcessFormEsDo entity = new ProcessFormEsDo();
        entity.setId(id);
        entity.setTaskId("taskId");
        entity.setTaskDefinitionName("taskDefinitionName");
        entity.setTaskDefinitionKey("taskDefinitionKey");
        entity.setTaskUrl("taskUrl");
        entity.setBusinessKey("businessKey");
        entity.setRevision(1);
        entity.setProcessInstanceId("processInstanceId");
        entity.setProcessDefinitionId("processDefinitionId");
        entity.setProcessDefinitionKey("processDefinitionKey");
        entity.setTitle("title");
        entity.setState("state");
        entity.setStartTime(nowTime);
        entity.setProcessInstanceStartTime(nowTime);
        entity.setEndTime(nowTime);
        entity.setLastUpdateDate(nowTime);
        entity.setCreatedBy("createdBy");
        entity.setCreatedDate(nowTime);
        entity.setCreatedByAccount("createdByAccount");
        entity.setCreatedByName("createdByName");
        entity.setLastUpdatedBy("lastUpdateBy");
        entity.setHandlers("handlers");
        entity.setUserIds("userIds");
        entity.setGroupIds("groupIds");
        entity.setStartUserId("startUserId");
        entity.setLookUpId("lookUpId");
        entity.setSuspensionState(2);
        entity.setIsSuspension(3);
        entity.setOperationState(4);
        entity.setLastUpdateName("lastUpdateName");

        processFormRepository.save(entity);
        return id;
    }

    public void delEntity(String id) {
        processFormRepository.deleteById(id);
    }

    public List<ProcessFormEsDo> getPageList(PageInfoDto pageInfo) {
        return getPageEntityList(new ProcessFormEsDo(), pageInfo);
    }

    public boolean indexExists(String indexName) {
        boolean result = elasticsearchOperations.indexExists(indexName);
        log.info("result: {}", result);

        return result;
    }

    public int batchDel() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(boolQueryBuilder);
        deleteQuery.setType("_doc");
        deleteQuery.setIndex("kibana_sample_data_flights");

        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("FlightNum", "JQ2XXQ5");
        boolQueryBuilder.must(queryBuilder);
        MatchQueryBuilder queryBuilder02 = QueryBuilders.matchQuery("OriginWeather", "Rain");
        boolQueryBuilder.must(queryBuilder02);

        elasticsearchOperations.delete(deleteQuery);
        // 可以使用它进行批量删除
        elasticsearchOperations.delete(deleteQuery, ProcessFormEsDo.class);

        return 0;
    }

    public List<ProcessFormEsDo> getPageEntityList(ProcessFormEsDo processFormEsDo, PageInfoDto pageInfo) {
        PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage() - 1, pageInfo.getPageSize());
        Page<ProcessFormEsDo> pageResult = processFormRepository.search(processFormEsDo, pageRequest);
        pageInfo.setTotalRows((int) pageResult.getTotalElements());
        return pageResult.getContent();
    }
}
