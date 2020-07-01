package com.suyh.entity;

import java.util.Date;

/**
 * 对所有的日期字段添加 Before After 两个字段
 */
public class CrmCustomerInfoFilter extends CrmCustomerInfo {

    private Date createdTimeBefore;
    private Date createdTimeAfter;
    private Date updatedTimeBefore;
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
