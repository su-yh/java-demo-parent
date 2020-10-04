package com.suyh.form.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 主要是表单需要，前端用来渲染。
 */
@Data
public class FormModelBase implements Serializable {
    public static final long serialVersionUID = 42L;

    private String type;
    private String varName;
    private Boolean enableNull;
    private String description;
    /**
     * Radio、Checkbox 时的选项
     */
    private List<String> options;

    /**
     * 数据库表主键ID
     */
    private String id;
    /**
     * 与部署ID相关联
     */
    private String deployId;
}
