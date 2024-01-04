package com.suyh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 客户基本信息表结构：内部客户、供应商、承运商 模糊查询实体
 *
 * @author suyh
 * @date 2020-05-03 11:58:55
 */
@ApiModel(value = "客户基本信息表结构：内部客户、供应商、承运商 模糊查询实体")
public class CrmCustomerInfoFilter extends CrmCustomerInfo {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "日期匹配边界。创建时间")
    private Date createdTimeBefore;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "日期匹配边界。创建时间")
    private Date createdTimeAfter;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "日期匹配边界。修改时间")
    private Date updatedTimeBefore;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "日期匹配边界。修改时间")
    private Date updatedTimeAfter;

    public Date getCreatedTimeBefore() {
        return createdTimeBefore;
    }

    public void setCreatedTimeBefore(Date createdTimeBefore) {
        this.createdTimeBefore = createdTimeBefore;
    }

    public Date getCreatedTimeAfter() {
        return createdTimeAfter;
    }

    public void setCreatedTimeAfter(Date createdTimeAfter) {
        this.createdTimeAfter = createdTimeAfter;
    }

    public Date getUpdatedTimeBefore() {
        return updatedTimeBefore;
    }

    public void setUpdatedTimeBefore(Date updatedTimeBefore) {
        this.updatedTimeBefore = updatedTimeBefore;
    }

    public Date getUpdatedTimeAfter() {
        return updatedTimeAfter;
    }

    public void setUpdatedTimeAfter(Date updatedTimeAfter) {
        this.updatedTimeAfter = updatedTimeAfter;
    }
}