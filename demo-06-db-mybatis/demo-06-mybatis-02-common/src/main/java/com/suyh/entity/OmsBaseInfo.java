package com.suyh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共数据库字段
 *
 * @author 苏雲弘
 * @Description:
 * @date 2020-05-07 11:02
 */
@ApiModel(description = "公共数据库字段")
public class OmsBaseInfo implements Serializable {
    /**
     * 建议序列化的class都给一个序列化的ID，这样可以保证序列化的成功，版本的兼容性。
     */
    private static final long serialVersionUID = 100000L;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", name = "createdBy")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createdTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ColumnType(jdbcType = JdbcType.TIMESTAMP)
    private Date createdTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", name = "updatedBy")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String updatedBy;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updatedTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ColumnType(jdbcType = JdbcType.TIMESTAMP)
    private Date updatedTime;
    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号", name = "versionCode")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String versionCode;
    /**
     * 预留字段
     */
    @ApiModelProperty(value = "预留字段", name = "item1")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String item1;
    /**
     * 预留字段
     */
    @ApiModelProperty(value = "预留字段", name = "item2")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String item2;
    /**
     * 预留字段
     */
    @ApiModelProperty(value = "预留字段", name = "item3")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String item3;
    /**
     * 预留字段
     */
    @ApiModelProperty(value = "预留字段", name = "item4")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String item4;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }
}
