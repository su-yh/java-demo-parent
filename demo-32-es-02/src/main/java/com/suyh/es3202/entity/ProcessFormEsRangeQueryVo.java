package com.suyh.es3202.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.suyh.es3202.util.EsFieldName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 范围查询实体对象
 *
 * @author 苏雲弘
 * @since 2020-12-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "ProcessFormEsRangeQueryVo", description = "ProcessFormEsRangeQueryVo 表单实体")
public class ProcessFormEsRangeQueryVo extends com.suyh.es3202.entity.ProcessFormEsDo {

    @EsFieldName(name = ProcessFormEsDo.FN_START_TIME)
    private EsQueryRange<Date> startTimeRange;

    @EsFieldName(name = ProcessFormEsDo.FN_END_TIME)
    private EsQueryRange<Date> endTimeRange;

    @EsFieldName(name = ProcessFormEsDo.FN_LAST_UPDATE_DATE)
    private EsQueryRange<Date> lastUpdateDateRange;

    @EsFieldName(name = ProcessFormEsDo.FN_CREATED_DATE)
    private EsQueryRange<Date> createdDateRange;

    @EsFieldName(name = ProcessFormEsDo.FN_PROC_INST_START_TIME)
    private EsQueryRange<Date> processInstanceStartTimeRange;
}