package com.suyh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 联系人信息表结构，多条联系人信息可以对应一个属主用户
 * 
 * @table: CRM_CONTACTS_INFO
 * @author: suyh
 * @date: 2020-05-03 11:58:55
 */
@ApiModel(value = "联系人信息表结构，多条联系人信息可以对应一个属主用户")
public class CrmContactsInfo extends BaseModel {
    /**
     * Column: CONTACTS_ID
     *   主键ID: 联系人ID
     */
    @ApiModelProperty(value = "主键ID: 联系人ID")
    private String contactsId;

    /**
     * Column: CUSTOMER_ID
     *   属主ID，映射到客户基本信息表
     */
    @ApiModelProperty(value = "属主ID，映射到客户基本信息表")
    private String customerId;

    /**
     * Column: CONTACTS_KINDS
     *   联系类别
     */
    @ApiModelProperty(value = "联系类别")
    private String contactsKinds;

    /**
     * Column: CONTACTS_NAME
     *   联系人姓名
     */
    @ApiModelProperty(value = "联系人姓名")
    private String contactsName;

    /**
     * Column: PHONE
     *   联系人电话
     */
    @ApiModelProperty(value = "联系人电话")
    private String phone;

    /**
     * Column: EMAIL
     *   邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * Column: QQ
     *   QQ 号
     */
    @ApiModelProperty(value = "QQ 号")
    private String qq;

    /**
     * Column: WECHAT
     *   微信
     */
    @ApiModelProperty(value = "微信")
    private String wechat;

    /**
     * Column: ADDRESS
     *   地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    public String getContactsId() {
        return contactsId;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId == null ? null : contactsId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getContactsKinds() {
        return contactsKinds;
    }

    public void setContactsKinds(String contactsKinds) {
        this.contactsKinds = contactsKinds == null ? null : contactsKinds.trim();
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName == null ? null : contactsName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}