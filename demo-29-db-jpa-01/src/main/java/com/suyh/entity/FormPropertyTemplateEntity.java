package com.suyh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "form_property_template")
public class FormPropertyTemplateEntity implements Serializable {
    static final long serialVersionUID = 42L;

    /**
     * 主键ID，JPA 必须要有主键才能使用。
     * 如果主键不是一个字段，那么需要处理复合主键
     *
     * public enum GenerationType{
     *     TABLE,    //使用一个特定的数据库表格来保存主键。
     *     SEQUENCE,    //根据底层数据库的序列来生成主键，条件是数据库支持序列。
     *     IDENTITY,    //主键由数据库自动生成（主要是自动增长型）
     *     AUTO   //主键由程序控制
     * }
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增
    private Long id;

    @Column(name = "business_key")
    private String businessKey;

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "required")
    private String required;

    @Column(name = "var_key")
    private String varKey;

    @Column(name = "description")
    private String description;

    @Column(name = "pattern")
    private String pattern;

    @Column(name = "value_custom")
    private String valueCustom;

    @Column(name = "value_custom_text")
    private String valueCustomText;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
