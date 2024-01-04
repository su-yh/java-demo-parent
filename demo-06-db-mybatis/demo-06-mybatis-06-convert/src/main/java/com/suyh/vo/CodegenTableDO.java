package com.suyh.vo;

import lombok.Data;

import java.util.List;

@Data
public class CodegenTableDO {
    private Boolean deleted;

    private Long id;

    private Long dataSourceConfigId;
    private Integer scene;
    private String tableName;
    private String tableComment;
    private String remark;
    private String moduleName;
    private String businessName;
    private String className;
    private String classComment;
    private String author;
    private Integer templateType;
    private Integer frontType;
    private Long parentMenuId;

    private String column;

    private List<String> listDesc;
}
