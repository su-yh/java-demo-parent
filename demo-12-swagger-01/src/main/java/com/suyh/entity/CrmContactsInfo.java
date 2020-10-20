package com.suyh.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @实体名称 
 * @数表名称 CRM_CONTACTS_INFO
 * @开发日期 2020-04-03
 * @技术服务 www.fwjava.com
 */
@ApiModel
@Data
public class CrmContactsInfo implements Serializable {

    /**
     * 主键：联系人ID(必填项)(主键ID)
     */
    // value: 在UI 页面上显示该字段的注释
    @ApiModelProperty(value = "联系人ID", example = "uuid")
    private String contactsId       = null;
    /**
     * 属主ID，映射到客户基本信息表(必填项)
     */
    @ApiModelProperty(value = "属主ID", example = "uuid")
    private String rfCustomerId     = null;
    /**
     * 联系类别
     */
    @ApiModelProperty(value = "联系类别", example = "contactsKins")
    private String contactsKinds    = null;
    /**
     * 联系人姓名
     */
    @ApiModelProperty(value = "联系人姓名", example = "联系人name")
    private String contactsName     = null;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", example = "17727448330")
    private String phone            = null;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", example = "787910081@qq.com")
    private String email            = null;
    /**
     * QQ号
     */
    @ApiModelProperty(value = "QQ", example = "787910081")
    private String qq               = null;
    /**
     * 微信
     */
    private String wechat           = null;
    /**
     * 地址
     */
    private String address          = null;
    /**
     * 
     */
    private String createdBy        = null;
    /**
     * 
     */
    private Date createdTime        = null;
    /**
     * 
     */
    private String updatedBy        = null;
    /**
     * 
     */
    private Date updatedTime        = null;
    /**
     * 排序
     */
    private String orderBy          = null;

    /*
     *--------------------------------------------------
     * Getter方法区
     *--------------------------------------------------
     */

    /**
     * 主键：联系人ID(必填项)(主键ID)
     */
    public String getContactsId() {
        return trim(contactsId);
    }
    /**
     * 属主ID，映射到客户基本信息表(必填项)
     */
    public String getRfCustomerId() {
        return trim(rfCustomerId);
    }
    /**
     * 联系类别
     */
    public String getContactsKinds() {
        return trim(contactsKinds);
    }
    /**
     * 联系人姓名
     */
    public String getContactsName() {
        return trim(contactsName);
    }
    /**
     * 电话
     */
    public String getPhone() {
        return trim(phone);
    }
    /**
     * 邮箱
     */
    public String getEmail() {
        return trim(email);
    }
    /**
     * QQ号
     */
    public String getQq() {
        return trim(qq);
    }
    /**
     * 微信
     */
    public String getWechat() {
        return trim(wechat);
    }
    /**
     * 地址
     */
    public String getAddress() {
        return trim(address);
    }
    /**
     * 
     */
    public String getCreatedBy() {
        return trim(createdBy);
    }
    /**
     * 
     */
    public Date getCreatedTime() {
        return createdTime;
    }
    /**
     * 
     */
    public String getUpdatedBy() {
        return trim(updatedBy);
    }
    /**
     * 
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }
    /**
     * 排序
     */
    public String getOrderBy() {
        return trim(orderBy);
    }

    /*
     *--------------------------------------------------
     * Setter方法区
     *--------------------------------------------------
     */

    /**
     * 主键：联系人ID(必填项)(主键ID)
     */
    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }
    /**
     * 属主ID，映射到客户基本信息表(必填项)
     */
    public void setRfCustomerId(String rfCustomerId) {
        this.rfCustomerId = rfCustomerId;
    }
    /**
     * 联系类别
     */
    public void setContactsKinds(String contactsKinds) {
        this.contactsKinds = contactsKinds;
    }
    /**
     * 联系人姓名
     */
    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }
    /**
     * 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * QQ号
     */
    public void setQq(String qq) {
        this.qq = qq;
    }
    /**
     * 微信
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    /**
     * 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * 
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    /**
     * 
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    /**
     * 
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    /**
     * 排序
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    /*
     *--------------------------------------------------
     * 常用自定义字段
     *--------------------------------------------------
     */
    /**
     * 主键：联系人ID(全模糊)
     */
    private String contactsIdLike          = null;
    /**
     * 属主ID，映射到客户基本信息表(全模糊)
     */
    private String rfCustomerIdLike        = null;
    /**
     * 联系类别(全模糊)
     */
    private String contactsKindsLike       = null;
    /**
     * 联系人姓名(全模糊)
     */
    private String contactsNameLike        = null;
    /**
     * 电话(全模糊)
     */
    private String phoneLike               = null;
    /**
     * 邮箱(全模糊)
     */
    private String emailLike               = null;
    /**
     * QQ号(全模糊)
     */
    private String qqLike                  = null;
    /**
     * 微信(全模糊)
     */
    private String wechatLike              = null;
    /**
     * 地址(全模糊)
     */
    private String addressLike             = null;
    /**
     * (全模糊)
     */
    private String createdByLike           = null;
    /**
     * (起始日期)
     */
    private String createdTimeBegin        = null;
    /**
     * (结束日期)
     */
    private String createdTimeEnd          = null;
    /**
     * (全模糊)
     */
    private String updatedByLike           = null;
    /**
     * (起始日期)
     */
    private String updatedTimeBegin        = null;
    /**
     * (结束日期)
     */
    private String updatedTimeEnd          = null;

    /**
     * 主键：联系人ID(全模糊)
     */
    public String getContactsIdLike() {
        return trim(contactsIdLike);
    }
    public void setContactsIdLike(String contactsIdLike) {
        this.contactsIdLike = contactsIdLike;
    }
    /**
     * 属主ID，映射到客户基本信息表(全模糊)
     */
    public String getRfCustomerIdLike() {
        return trim(rfCustomerIdLike);
    }
    public void setRfCustomerIdLike(String rfCustomerIdLike) {
        this.rfCustomerIdLike = rfCustomerIdLike;
    }
    /**
     * 联系类别(全模糊)
     */
    public String getContactsKindsLike() {
        return trim(contactsKindsLike);
    }
    public void setContactsKindsLike(String contactsKindsLike) {
        this.contactsKindsLike = contactsKindsLike;
    }
    /**
     * 联系人姓名(全模糊)
     */
    public String getContactsNameLike() {
        return trim(contactsNameLike);
    }
    public void setContactsNameLike(String contactsNameLike) {
        this.contactsNameLike = contactsNameLike;
    }
    /**
     * 电话(全模糊)
     */
    public String getPhoneLike() {
        return trim(phoneLike);
    }
    public void setPhoneLike(String phoneLike) {
        this.phoneLike = phoneLike;
    }
    /**
     * 邮箱(全模糊)
     */
    public String getEmailLike() {
        return trim(emailLike);
    }
    public void setEmailLike(String emailLike) {
        this.emailLike = emailLike;
    }
    /**
     * QQ号(全模糊)
     */
    public String getQqLike() {
        return trim(qqLike);
    }
    public void setQqLike(String qqLike) {
        this.qqLike = qqLike;
    }
    /**
     * 微信(全模糊)
     */
    public String getWechatLike() {
        return trim(wechatLike);
    }
    public void setWechatLike(String wechatLike) {
        this.wechatLike = wechatLike;
    }
    /**
     * 地址(全模糊)
     */
    public String getAddressLike() {
        return trim(addressLike);
    }
    public void setAddressLike(String addressLike) {
        this.addressLike = addressLike;
    }
    /**
     * (全模糊)
     */
    public String getCreatedByLike() {
        return trim(createdByLike);
    }
    public void setCreatedByLike(String createdByLike) {
        this.createdByLike = createdByLike;
    }
    /**
     * (起始日期)
     */
    public String getCreatedTimeBegin() {
        return trim(createdTimeBegin);
    }
    public void setCreatedTimeBegin(String createdTimeBegin) {
        this.createdTimeBegin = createdTimeBegin;
    }
    /**
     * (结束日期)
     */
    public String getCreatedTimeEnd() {
        return trim(createdTimeEnd);
    }
    public void setCreatedTimeEnd(String createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }
    /**
     * (格式化)
     */
    public String getCreatedTimeChar() {
        return getDate(createdTime);
    }
    public void setCreatedTimeChar(String createdTimeChar) {
        this.createdTime = getDate(createdTimeChar);
    }
    /**
     * (格式化)
     */
    public String getCreatedTimeCharAll() {
        return getDateTime(createdTime);
    }
    public void setCreatedTimeCharAll(String createdTimeCharAll) {
        this.createdTime = getDate(createdTimeCharAll);
    }
    /**
     * (全模糊)
     */
    public String getUpdatedByLike() {
        return trim(updatedByLike);
    }
    public void setUpdatedByLike(String updatedByLike) {
        this.updatedByLike = updatedByLike;
    }
    /**
     * (起始日期)
     */
    public String getUpdatedTimeBegin() {
        return trim(updatedTimeBegin);
    }
    public void setUpdatedTimeBegin(String updatedTimeBegin) {
        this.updatedTimeBegin = updatedTimeBegin;
    }
    /**
     * (结束日期)
     */
    public String getUpdatedTimeEnd() {
        return trim(updatedTimeEnd);
    }
    public void setUpdatedTimeEnd(String updatedTimeEnd) {
        this.updatedTimeEnd = updatedTimeEnd;
    }
    /**
     * (格式化)
     */
    public String getUpdatedTimeChar() {
        return getDate(updatedTime);
    }
    public void setUpdatedTimeChar(String updatedTimeChar) {
        this.updatedTime = getDate(updatedTimeChar);
    }
    /**
     * (格式化)
     */
    public String getUpdatedTimeCharAll() {
        return getDateTime(updatedTime);
    }
    public void setUpdatedTimeCharAll(String updatedTimeCharAll) {
        this.updatedTime = getDate(updatedTimeCharAll);
    }

    /*
     *--------------------------------------------------
     * 应用小方法
     *--------------------------------------------------
     */

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public String trim(String input)
    {
        return input==null?null:input.trim();
    }
    
    public String getDate(Date date)
    {
        if( null == date ) return "";
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    public String getDateTime(Date date)
    {
        if( null == date ) return "";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    public Date getDate(String date)
    {
        if( null == date || date.trim().isEmpty() ) return null;
        date = date.trim();
        Date result = null;
        try {
            if( date.length() >= 19 )
            {
                result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            }
            else if( date.length() == 10 )
            {
                result = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            }
        }
        catch (ParseException e) 
        {
            
        }
        return result;
    }

}


/** 
------------------------------------------------------
 Copy专用区
------------------------------------------------------

------------------------------------------------------------------------------------------------------------
  Setter方法
------------------------------------------------------------------------------------------------------------

// 
CrmContactsInfo crmContactsInfo = new CrmContactsInfo();

// 主键：联系人ID(必填项)(主键ID)
crmContactsInfo.setContactsId(  );
// 属主ID，映射到客户基本信息表(必填项)
crmContactsInfo.setRfCustomerId(  );
// 联系类别
crmContactsInfo.setContactsKinds(  );
// 联系人姓名
crmContactsInfo.setContactsName(  );
// 电话
crmContactsInfo.setPhone(  );
// 邮箱
crmContactsInfo.setEmail(  );
// QQ号
crmContactsInfo.setQq(  );
// 微信
crmContactsInfo.setWechat(  );
// 地址
crmContactsInfo.setAddress(  );
// 
crmContactsInfo.setCreatedBy(  );
// 
crmContactsInfo.setCreatedTime(  );
// 
crmContactsInfo.setUpdatedBy(  );
// 
crmContactsInfo.setUpdatedTime(  );


//------ 自定义部分 ------

// 主键：联系人ID(全模糊)
crmContactsInfo.setContactsIdLike(  );
// 属主ID，映射到客户基本信息表(全模糊)
crmContactsInfo.setRfCustomerIdLike(  );
// 联系类别(全模糊)
crmContactsInfo.setContactsKindsLike(  );
// 联系人姓名(全模糊)
crmContactsInfo.setContactsNameLike(  );
// 电话(全模糊)
crmContactsInfo.setPhoneLike(  );
// 邮箱(全模糊)
crmContactsInfo.setEmailLike(  );
// QQ号(全模糊)
crmContactsInfo.setQqLike(  );
// 微信(全模糊)
crmContactsInfo.setWechatLike(  );
// 地址(全模糊)
crmContactsInfo.setAddressLike(  );
// (全模糊)
crmContactsInfo.setCreatedByLike(  );
// (起始日期)
crmContactsInfo.setCreatedTimeBegin(  );
// (结束日期)
crmContactsInfo.setCreatedTimeEnd(  );
// (格式化)
crmContactsInfo.setCreatedTimeChar(  );
// (全模糊)
crmContactsInfo.setUpdatedByLike(  );
// (起始日期)
crmContactsInfo.setUpdatedTimeBegin(  );
// (结束日期)
crmContactsInfo.setUpdatedTimeEnd(  );
// (格式化)
crmContactsInfo.setUpdatedTimeChar(  );


------------------------------------------------------------------------------------------------------------
  Getter方法
------------------------------------------------------------------------------------------------------------

// 
CrmContactsInfo crmContactsInfo = new CrmContactsInfo();

// 主键：联系人ID(必填项)(主键ID)
crmContactsInfo.getContactsId();
// 属主ID，映射到客户基本信息表(必填项)
crmContactsInfo.getRfCustomerId();
// 联系类别
crmContactsInfo.getContactsKinds();
// 联系人姓名
crmContactsInfo.getContactsName();
// 电话
crmContactsInfo.getPhone();
// 邮箱
crmContactsInfo.getEmail();
// QQ号
crmContactsInfo.getQq();
// 微信
crmContactsInfo.getWechat();
// 地址
crmContactsInfo.getAddress();
// 
crmContactsInfo.getCreatedBy();
// 
crmContactsInfo.getCreatedTime();
// 
crmContactsInfo.getUpdatedBy();
// 
crmContactsInfo.getUpdatedTime();


//------ 自定义部分 ------

// 主键：联系人ID(全模糊)
crmContactsInfo.getContactsIdLike();
// 属主ID，映射到客户基本信息表(全模糊)
crmContactsInfo.getRfCustomerIdLike();
// 联系类别(全模糊)
crmContactsInfo.getContactsKindsLike();
// 联系人姓名(全模糊)
crmContactsInfo.getContactsNameLike();
// 电话(全模糊)
crmContactsInfo.getPhoneLike();
// 邮箱(全模糊)
crmContactsInfo.getEmailLike();
// QQ号(全模糊)
crmContactsInfo.getQqLike();
// 微信(全模糊)
crmContactsInfo.getWechatLike();
// 地址(全模糊)
crmContactsInfo.getAddressLike();
// (全模糊)
crmContactsInfo.getCreatedByLike();
// (起始日期)
crmContactsInfo.getCreatedTimeBegin();
// (结束日期)
crmContactsInfo.getCreatedTimeEnd();
// (格式化)
crmContactsInfo.getCreatedTimeChar();
// (全模糊)
crmContactsInfo.getUpdatedByLike();
// (起始日期)
crmContactsInfo.getUpdatedTimeBegin();
// (结束日期)
crmContactsInfo.getUpdatedTimeEnd();
// (格式化)
crmContactsInfo.getUpdatedTimeChar();


------------------------------------------------------------------------------------------------------------
  Getter Setter方法
------------------------------------------------------------------------------------------------------------

// 
CrmContactsInfo crmContactsInfo = new CrmContactsInfo();

// 主键：联系人ID(必填项)(主键ID)
crmContactsInfo.setContactsId( crmContactsInfo2.getContactsId() );
// 属主ID，映射到客户基本信息表(必填项)
crmContactsInfo.setRfCustomerId( crmContactsInfo2.getRfCustomerId() );
// 联系类别
crmContactsInfo.setContactsKinds( crmContactsInfo2.getContactsKinds() );
// 联系人姓名
crmContactsInfo.setContactsName( crmContactsInfo2.getContactsName() );
// 电话
crmContactsInfo.setPhone( crmContactsInfo2.getPhone() );
// 邮箱
crmContactsInfo.setEmail( crmContactsInfo2.getEmail() );
// QQ号
crmContactsInfo.setQq( crmContactsInfo2.getQq() );
// 微信
crmContactsInfo.setWechat( crmContactsInfo2.getWechat() );
// 地址
crmContactsInfo.setAddress( crmContactsInfo2.getAddress() );
// 
crmContactsInfo.setCreatedBy( crmContactsInfo2.getCreatedBy() );
// 
crmContactsInfo.setCreatedTime( crmContactsInfo2.getCreatedTime() );
// 
crmContactsInfo.setUpdatedBy( crmContactsInfo2.getUpdatedBy() );
// 
crmContactsInfo.setUpdatedTime( crmContactsInfo2.getUpdatedTime() );


//------ 自定义部分 ------

// 主键：联系人ID(全模糊)
crmContactsInfo.setContactsIdLike( crmContactsInfo2.getContactsIdLike() );
// 属主ID，映射到客户基本信息表(全模糊)
crmContactsInfo.setRfCustomerIdLike( crmContactsInfo2.getRfCustomerIdLike() );
// 联系类别(全模糊)
crmContactsInfo.setContactsKindsLike( crmContactsInfo2.getContactsKindsLike() );
// 联系人姓名(全模糊)
crmContactsInfo.setContactsNameLike( crmContactsInfo2.getContactsNameLike() );
// 电话(全模糊)
crmContactsInfo.setPhoneLike( crmContactsInfo2.getPhoneLike() );
// 邮箱(全模糊)
crmContactsInfo.setEmailLike( crmContactsInfo2.getEmailLike() );
// QQ号(全模糊)
crmContactsInfo.setQqLike( crmContactsInfo2.getQqLike() );
// 微信(全模糊)
crmContactsInfo.setWechatLike( crmContactsInfo2.getWechatLike() );
// 地址(全模糊)
crmContactsInfo.setAddressLike( crmContactsInfo2.getAddressLike() );
// (全模糊)
crmContactsInfo.setCreatedByLike( crmContactsInfo2.getCreatedByLike() );
// (起始日期)
crmContactsInfo.setCreatedTimeBegin( crmContactsInfo2.getCreatedTimeBegin() );
// (结束日期)
crmContactsInfo.setCreatedTimeEnd( crmContactsInfo2.getCreatedTimeEnd() );
// (格式化)
crmContactsInfo.setCreatedTimeChar( crmContactsInfo2.getCreatedTimeChar() );
// (全模糊)
crmContactsInfo.setUpdatedByLike( crmContactsInfo2.getUpdatedByLike() );
// (起始日期)
crmContactsInfo.setUpdatedTimeBegin( crmContactsInfo2.getUpdatedTimeBegin() );
// (结束日期)
crmContactsInfo.setUpdatedTimeEnd( crmContactsInfo2.getUpdatedTimeEnd() );
// (格式化)
crmContactsInfo.setUpdatedTimeChar( crmContactsInfo2.getUpdatedTimeChar() );



------------------------------------------------------------------------------------------------------------
 HTML标签区
------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------
  属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键：联系人ID -->
<input name="contactsId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input name="rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 联系类别 -->
<input name="contactsKinds" value="" type="text" maxlength="20"/>
<!-- 联系人姓名 -->
<input name="contactsName" value="" type="text" maxlength="30"/>
<!-- 电话 -->
<input name="phone" value="" type="text" maxlength="20"/>
<!-- 邮箱 -->
<input name="email" value="" type="text" maxlength="30"/>
<!-- QQ号 -->
<input name="qq" value="" type="text" maxlength="20"/>
<!-- 微信 -->
<input name="wechat" value="" type="text" maxlength="30"/>
<!-- 地址 -->
<input name="address" value="" type="text" maxlength="128"/>
<!--  -->
<input name="createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input name="updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="updatedTime" value="" type="text" maxlength="36"/>


------------------------------------------------------------------------------------------------------------
  表名 + 属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键：联系人ID -->
<input name="crmContactsInfo.contactsId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input name="crmContactsInfo.rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 联系类别 -->
<input name="crmContactsInfo.contactsKinds" value="" type="text" maxlength="20"/>
<!-- 联系人姓名 -->
<input name="crmContactsInfo.contactsName" value="" type="text" maxlength="30"/>
<!-- 电话 -->
<input name="crmContactsInfo.phone" value="" type="text" maxlength="20"/>
<!-- 邮箱 -->
<input name="crmContactsInfo.email" value="" type="text" maxlength="30"/>
<!-- QQ号 -->
<input name="crmContactsInfo.qq" value="" type="text" maxlength="20"/>
<!-- 微信 -->
<input name="crmContactsInfo.wechat" value="" type="text" maxlength="30"/>
<!-- 地址 -->
<input name="crmContactsInfo.address" value="" type="text" maxlength="128"/>
<!--  -->
<input name="crmContactsInfo.createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmContactsInfo.createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmContactsInfo.updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmContactsInfo.updatedTime" value="" type="text" maxlength="36"/>


------------------------------------------------------------------------------------------------------------
  ID + 属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键：联系人ID -->
<input id="CCI_CONTACTS_ID" name="contactsId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input id="CCI_RF_CUSTOMER_ID" name="rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 联系类别 -->
<input id="CCI_CONTACTS_KINDS" name="contactsKinds" value="" type="text" maxlength="20"/>
<!-- 联系人姓名 -->
<input id="CCI_CONTACTS_NAME" name="contactsName" value="" type="text" maxlength="30"/>
<!-- 电话 -->
<input id="CCI_PHONE" name="phone" value="" type="text" maxlength="20"/>
<!-- 邮箱 -->
<input id="CCI_EMAIL" name="email" value="" type="text" maxlength="30"/>
<!-- QQ号 -->
<input id="CCI_QQ" name="qq" value="" type="text" maxlength="20"/>
<!-- 微信 -->
<input id="CCI_WECHAT" name="wechat" value="" type="text" maxlength="30"/>
<!-- 地址 -->
<input id="CCI_ADDRESS" name="address" value="" type="text" maxlength="128"/>
<!--  -->
<input id="CCI_CREATED_BY" name="createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CCI_CREATED_TIME" name="createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CCI_UPDATED_BY" name="updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CCI_UPDATED_TIME" name="updatedTime" value="" type="text" maxlength="36"/>


------------------------------------------------------------------------------------------------------------
  ID + 表名 + 属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键：联系人ID -->
<input id="CCI_CONTACTS_ID" name="crmContactsInfo.contactsId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input id="CCI_RF_CUSTOMER_ID" name="crmContactsInfo.rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 联系类别 -->
<input id="CCI_CONTACTS_KINDS" name="crmContactsInfo.contactsKinds" value="" type="text" maxlength="20"/>
<!-- 联系人姓名 -->
<input id="CCI_CONTACTS_NAME" name="crmContactsInfo.contactsName" value="" type="text" maxlength="30"/>
<!-- 电话 -->
<input id="CCI_PHONE" name="crmContactsInfo.phone" value="" type="text" maxlength="20"/>
<!-- 邮箱 -->
<input id="CCI_EMAIL" name="crmContactsInfo.email" value="" type="text" maxlength="30"/>
<!-- QQ号 -->
<input id="CCI_QQ" name="crmContactsInfo.qq" value="" type="text" maxlength="20"/>
<!-- 微信 -->
<input id="CCI_WECHAT" name="crmContactsInfo.wechat" value="" type="text" maxlength="30"/>
<!-- 地址 -->
<input id="CCI_ADDRESS" name="crmContactsInfo.address" value="" type="text" maxlength="128"/>
<!--  -->
<input id="CCI_CREATED_BY" name="crmContactsInfo.createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CCI_CREATED_TIME" name="crmContactsInfo.createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CCI_UPDATED_BY" name="crmContactsInfo.updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CCI_UPDATED_TIME" name="crmContactsInfo.updatedTime" value="" type="text" maxlength="36"/>




--------------------------------------------------------
 */


    