package com.suyh.es3202.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 苏雲弘
 * @since 2020-11-26
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
// 指定自定义setting，主要是自定义分词，将json数据填写到配置文件中，
// 然后在启动的时候会读取该文件，在创建索引的时候同时设置此参数
@Setting(settingPath = "es/settings/processFormAnalyzer.json")
// 按bean 对象的属性进行添加索引前缀
// @Document(indexName = "#{esConfigProperties.indexPrefix}_tpl_wf_procform_t", type = "_doc", createIndex = false)
@Document(indexName = "tpl_wf_procform_t", type = "_doc", createIndex = false)
@ApiModel(value = "ProcessFormEsDo", description = "ProcessFormEsDo 表单实体")
public class ProcessFormEsDo implements Serializable {

    public static final String CUSTOM_ANALYZER = "processFormAnalyzer";

    // 对应ES 中属性名称
    public static final String FN_ES_LAST_UPDATE_DATE = "lastUpdateDate";
    public static final String FN_ES_START_TIME = "startTime";
    public static final String FN_ES_END_TIME = "endTime";
    public static final String FN_ES_CREATED_DATE = "createdDate";
    public static final String FN_ES_PROC_INST_START_TIME = "processInstanceStartTime";

    @Id
    private String id;

    /**
     * taskId
     * normalizer = "lowercase" 对于Keyword 类型的查询，可以忽略大小写。该值需要在settings 中进行设置。详见：processFormAnalyzer.json  【normalizer】
     */
    @Field(type = FieldType.Keyword, normalizer = "lowercase")
    private String taskId;

    /**
     * taskName
     */
    @Field(type = FieldType.Text)
    private String taskDefinitionName;

    /**
     * taskDefinitionKey
     */
    @Field(type = FieldType.Text)
    private String taskDefinitionKey;

    /**
     * taskUrl
     */
    @Field(type = FieldType.Text)
    private String taskUrl;

    /**
     * businessKey
     */
    @Field(type = FieldType.Text)
    private String businessKey;

    /**
     * 流程实例id
     */
    @Field(type = FieldType.Keyword)
    private String processInstanceId;

    /**
     * processDefinitionId
     */
    @Field(type = FieldType.Keyword)
    private String processDefinitionId;

    /**
     * processDefinitionKey
     */
    @Field(type = FieldType.Text)
    private String processDefinitionKey;

    /**
     * 流程标题
     */
    @Field(type = FieldType.Text)
    private String title;

    /**
     * 流程状态
     */
    @Field(type = FieldType.Keyword)
    private String state;

    /**
     * 开始时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "epoch_millis||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date startTime;

    /**
     * 流程发起时间
     */
    @Field(name = FN_ES_PROC_INST_START_TIME, type = FieldType.Date, format = DateFormat.custom,
            pattern = "epoch_millis||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date processInstanceStartTime;

    /**
     * 结束时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "epoch_millis||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date endTime;

    /**
     * 最后更新时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "epoch_millis||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date lastUpdateDate;

    /**
     * 表单创建人
     */
    @Field(type = FieldType.Keyword)
    private String createdBy;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "epoch_millis||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createdDate;

    /**
     * 表单创建人的账号
     */
    @Field(type = FieldType.Text)
    private String createdByAccount;

    /**
     * 表单创建人的名字
     */
    @Field(type = FieldType.Text)
    private String createdByName;

    /**
     * 最后更新人
     */
    @Field(type = FieldType.Text)
    private String lastUpdatedBy;

    /**
     * 当前处理人
     * 使用自定义的分词器
     */
    @Field(type = FieldType.Text, analyzer = CUSTOM_ANALYZER)
    private String handlers;

    /**
     * 当前处理人ids
     * 使用自定义的分词器
     */
    @Field(type = FieldType.Text, analyzer = CUSTOM_ANALYZER)
    private String userIds;

    /**
     * 处理人群组ids
     * 使用自定义的分词器
     */
    @Field(type = FieldType.Text, analyzer = CUSTOM_ANALYZER)
    private String groupIds;

    /**
     * 流程发起人
     */
    @Field(type = FieldType.Text)
    private String startUserId;

    /**
     * 分类id
     */
    @Field(type = FieldType.Text)
    private String lookUpId;

    /**
     * 挂起状态
     */
    @Field(type = FieldType.Integer)
    private Integer isSuspension;

    /**
     * 最后修改人
     */
    @Field(type = FieldType.Text)
    private String lastUpdateName;

    /**
     * 修改版本
     */
    @Field(type = FieldType.Integer)
    private Integer revision;

    /**
     * 操作状态
     */
    @Field(type = FieldType.Integer)
    private Integer operationState;

    /**
     * 挂起状态
     */
    @Field(type = FieldType.Integer)
    private Integer suspensionState;

    /**
     * 插入时间戳
     */
    @Field(type = FieldType.Long)
    private Long insertTimestamp;

    /**
     * 修改时间戳
     */
    @Field(type = FieldType.Long)
    private Long lastUpdateTimestamp;
}


