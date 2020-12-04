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

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 苏雲弘
 * @since 2020-11-26
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "tpl_wf_procform_t", type = "_doc", createIndex = false)
@ApiModel(value = "ProcessFormEsDo", description = "ProcessFormEsDo 表单实体")
public class ProcessFormEsDo implements Serializable {

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
     */
    @Field(type = FieldType.Keyword)
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
            pattern = "strict_date_optional_time||epoch_millis||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 流程发起时间
     */
    @Field(name = FN_ES_PROC_INST_START_TIME, type = FieldType.Date, format = DateFormat.custom,
            pattern = "strict_date_optional_time||epoch_millis||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date processInstanceStartTime;

    /**
     * 结束时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "strict_date_optional_time||epoch_millis||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 最后更新时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "strict_date_optional_time||epoch_millis||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
            pattern = "strict_date_optional_time||epoch_millis||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
     */
    @Field(type = FieldType.Text)
    private String handlers;

    /**
     * 当前处理人ids
     */
    @Field(type = FieldType.Text)
    private String userIds;

    /**
     * 处理人群组ids
     */
    @Field(type = FieldType.Text)
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
     * 抄送人ids
     * 这里要注意一下，entity中的命名为 CCUserIds，这里处理成小写主要是映射到es 会有些问题。
     * 无法正常映射过去。
     */
    @Field(type = FieldType.Text)
    private String ccUserIds;

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
