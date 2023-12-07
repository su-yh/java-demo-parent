package com.suyh.vo;

import lombok.Data;

import java.util.List;

@Data
public class CodegenColumnDO {
    private Boolean deleted;
    private Long id;
    private Long tableId;
    private String columnName;
    private String dataType;
    private String columnComment;
    private Boolean nullable;
    private Boolean primaryKey;
    private Boolean autoIncrement;
    private Integer ordinalPosition;
    private String javaType;
    private String javaField;
    private String dictType;
    private String example;
    private Boolean createOperation;
    private Boolean updateOperation;
    private Boolean listOperation;
    private String listOperationCondition;
    private Boolean listOperationResult;
    private String htmlType;

    private List<String> listDesc;
}
