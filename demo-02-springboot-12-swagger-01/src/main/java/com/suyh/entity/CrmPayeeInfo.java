package com.suyh.entity;


import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @实体名称 
 * @数表名称 CRM_PAYEE_INFO
 * @开发日期 2020-04-03
 * @技术服务 www.fwjava.com
 */
@ApiModel
public class CrmPayeeInfo implements Serializable {

    /**
     * 主键ID：收款人信息ID(必填项)(主键ID)
     */
    private String payeeInfoId        = null;
    /**
     * 属主ID，映射到客户基本信息表(必填项)
     */
    private String rfCustomerId       = null;
    /**
     * 开户行名称
     */
    private String openingBank        = null;
    /**
     * 银行帐号-收款帐户
     */
    private String bankAccountCode    = null;
    /**
     * 帐户名
     */
    private String bankAccountName    = null;
    /**
     * 
     */
    private String createdBy          = null;
    /**
     * 
     */
    private Date createdTime          = null;
    /**
     * 
     */
    private String updatedBy          = null;
    /**
     * 
     */
    private Date updatedTime          = null;
    /**
     * 排序
     */
    private String orderBy            = null;

    /*
     *--------------------------------------------------
     * Getter方法区
     *--------------------------------------------------
     */

    /**
     * 主键ID：收款人信息ID(必填项)(主键ID)
     */
    public String getPayeeInfoId() {
        return trim(payeeInfoId);
    }
    /**
     * 属主ID，映射到客户基本信息表(必填项)
     */
    public String getRfCustomerId() {
        return trim(rfCustomerId);
    }
    /**
     * 开户行名称
     */
    public String getOpeningBank() {
        return trim(openingBank);
    }
    /**
     * 银行帐号-收款帐户
     */
    public String getBankAccountCode() {
        return trim(bankAccountCode);
    }
    /**
     * 帐户名
     */
    public String getBankAccountName() {
        return trim(bankAccountName);
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
     * 主键ID：收款人信息ID(必填项)(主键ID)
     */
    public void setPayeeInfoId(String payeeInfoId) {
        this.payeeInfoId = payeeInfoId;
    }
    /**
     * 属主ID，映射到客户基本信息表(必填项)
     */
    public void setRfCustomerId(String rfCustomerId) {
        this.rfCustomerId = rfCustomerId;
    }
    /**
     * 开户行名称
     */
    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }
    /**
     * 银行帐号-收款帐户
     */
    public void setBankAccountCode(String bankAccountCode) {
        this.bankAccountCode = bankAccountCode;
    }
    /**
     * 帐户名
     */
    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
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
     * 主键ID：收款人信息ID(全模糊)
     */
    private String payeeInfoIdLike           = null;
    /**
     * 属主ID，映射到客户基本信息表(全模糊)
     */
    private String rfCustomerIdLike          = null;
    /**
     * 开户行名称(全模糊)
     */
    private String openingBankLike           = null;
    /**
     * 银行帐号-收款帐户(全模糊)
     */
    private String bankAccountCodeLike       = null;
    /**
     * 帐户名(全模糊)
     */
    private String bankAccountNameLike       = null;
    /**
     * (全模糊)
     */
    private String createdByLike             = null;
    /**
     * (起始日期)
     */
    private String createdTimeBegin          = null;
    /**
     * (结束日期)
     */
    private String createdTimeEnd            = null;
    /**
     * (全模糊)
     */
    private String updatedByLike             = null;
    /**
     * (起始日期)
     */
    private String updatedTimeBegin          = null;
    /**
     * (结束日期)
     */
    private String updatedTimeEnd            = null;

    /**
     * 主键ID：收款人信息ID(全模糊)
     */
    public String getPayeeInfoIdLike() {
        return trim(payeeInfoIdLike);
    }
    public void setPayeeInfoIdLike(String payeeInfoIdLike) {
        this.payeeInfoIdLike = payeeInfoIdLike;
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
     * 开户行名称(全模糊)
     */
    public String getOpeningBankLike() {
        return trim(openingBankLike);
    }
    public void setOpeningBankLike(String openingBankLike) {
        this.openingBankLike = openingBankLike;
    }
    /**
     * 银行帐号-收款帐户(全模糊)
     */
    public String getBankAccountCodeLike() {
        return trim(bankAccountCodeLike);
    }
    public void setBankAccountCodeLike(String bankAccountCodeLike) {
        this.bankAccountCodeLike = bankAccountCodeLike;
    }
    /**
     * 帐户名(全模糊)
     */
    public String getBankAccountNameLike() {
        return trim(bankAccountNameLike);
    }
    public void setBankAccountNameLike(String bankAccountNameLike) {
        this.bankAccountNameLike = bankAccountNameLike;
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
CrmPayeeInfo crmPayeeInfo = new CrmPayeeInfo();

// 主键ID：收款人信息ID(必填项)(主键ID)
crmPayeeInfo.setPayeeInfoId(  );
// 属主ID，映射到客户基本信息表(必填项)
crmPayeeInfo.setRfCustomerId(  );
// 开户行名称
crmPayeeInfo.setOpeningBank(  );
// 银行帐号-收款帐户
crmPayeeInfo.setBankAccountCode(  );
// 帐户名
crmPayeeInfo.setBankAccountName(  );
// 
crmPayeeInfo.setCreatedBy(  );
// 
crmPayeeInfo.setCreatedTime(  );
// 
crmPayeeInfo.setUpdatedBy(  );
// 
crmPayeeInfo.setUpdatedTime(  );


//------ 自定义部分 ------

// 主键ID：收款人信息ID(全模糊)
crmPayeeInfo.setPayeeInfoIdLike(  );
// 属主ID，映射到客户基本信息表(全模糊)
crmPayeeInfo.setRfCustomerIdLike(  );
// 开户行名称(全模糊)
crmPayeeInfo.setOpeningBankLike(  );
// 银行帐号-收款帐户(全模糊)
crmPayeeInfo.setBankAccountCodeLike(  );
// 帐户名(全模糊)
crmPayeeInfo.setBankAccountNameLike(  );
// (全模糊)
crmPayeeInfo.setCreatedByLike(  );
// (起始日期)
crmPayeeInfo.setCreatedTimeBegin(  );
// (结束日期)
crmPayeeInfo.setCreatedTimeEnd(  );
// (格式化)
crmPayeeInfo.setCreatedTimeChar(  );
// (全模糊)
crmPayeeInfo.setUpdatedByLike(  );
// (起始日期)
crmPayeeInfo.setUpdatedTimeBegin(  );
// (结束日期)
crmPayeeInfo.setUpdatedTimeEnd(  );
// (格式化)
crmPayeeInfo.setUpdatedTimeChar(  );


------------------------------------------------------------------------------------------------------------
  Getter方法
------------------------------------------------------------------------------------------------------------

// 
CrmPayeeInfo crmPayeeInfo = new CrmPayeeInfo();

// 主键ID：收款人信息ID(必填项)(主键ID)
crmPayeeInfo.getPayeeInfoId();
// 属主ID，映射到客户基本信息表(必填项)
crmPayeeInfo.getRfCustomerId();
// 开户行名称
crmPayeeInfo.getOpeningBank();
// 银行帐号-收款帐户
crmPayeeInfo.getBankAccountCode();
// 帐户名
crmPayeeInfo.getBankAccountName();
// 
crmPayeeInfo.getCreatedBy();
// 
crmPayeeInfo.getCreatedTime();
// 
crmPayeeInfo.getUpdatedBy();
// 
crmPayeeInfo.getUpdatedTime();


//------ 自定义部分 ------

// 主键ID：收款人信息ID(全模糊)
crmPayeeInfo.getPayeeInfoIdLike();
// 属主ID，映射到客户基本信息表(全模糊)
crmPayeeInfo.getRfCustomerIdLike();
// 开户行名称(全模糊)
crmPayeeInfo.getOpeningBankLike();
// 银行帐号-收款帐户(全模糊)
crmPayeeInfo.getBankAccountCodeLike();
// 帐户名(全模糊)
crmPayeeInfo.getBankAccountNameLike();
// (全模糊)
crmPayeeInfo.getCreatedByLike();
// (起始日期)
crmPayeeInfo.getCreatedTimeBegin();
// (结束日期)
crmPayeeInfo.getCreatedTimeEnd();
// (格式化)
crmPayeeInfo.getCreatedTimeChar();
// (全模糊)
crmPayeeInfo.getUpdatedByLike();
// (起始日期)
crmPayeeInfo.getUpdatedTimeBegin();
// (结束日期)
crmPayeeInfo.getUpdatedTimeEnd();
// (格式化)
crmPayeeInfo.getUpdatedTimeChar();


------------------------------------------------------------------------------------------------------------
  Getter Setter方法
------------------------------------------------------------------------------------------------------------

// 
CrmPayeeInfo crmPayeeInfo = new CrmPayeeInfo();

// 主键ID：收款人信息ID(必填项)(主键ID)
crmPayeeInfo.setPayeeInfoId( crmPayeeInfo2.getPayeeInfoId() );
// 属主ID，映射到客户基本信息表(必填项)
crmPayeeInfo.setRfCustomerId( crmPayeeInfo2.getRfCustomerId() );
// 开户行名称
crmPayeeInfo.setOpeningBank( crmPayeeInfo2.getOpeningBank() );
// 银行帐号-收款帐户
crmPayeeInfo.setBankAccountCode( crmPayeeInfo2.getBankAccountCode() );
// 帐户名
crmPayeeInfo.setBankAccountName( crmPayeeInfo2.getBankAccountName() );
// 
crmPayeeInfo.setCreatedBy( crmPayeeInfo2.getCreatedBy() );
// 
crmPayeeInfo.setCreatedTime( crmPayeeInfo2.getCreatedTime() );
// 
crmPayeeInfo.setUpdatedBy( crmPayeeInfo2.getUpdatedBy() );
// 
crmPayeeInfo.setUpdatedTime( crmPayeeInfo2.getUpdatedTime() );


//------ 自定义部分 ------

// 主键ID：收款人信息ID(全模糊)
crmPayeeInfo.setPayeeInfoIdLike( crmPayeeInfo2.getPayeeInfoIdLike() );
// 属主ID，映射到客户基本信息表(全模糊)
crmPayeeInfo.setRfCustomerIdLike( crmPayeeInfo2.getRfCustomerIdLike() );
// 开户行名称(全模糊)
crmPayeeInfo.setOpeningBankLike( crmPayeeInfo2.getOpeningBankLike() );
// 银行帐号-收款帐户(全模糊)
crmPayeeInfo.setBankAccountCodeLike( crmPayeeInfo2.getBankAccountCodeLike() );
// 帐户名(全模糊)
crmPayeeInfo.setBankAccountNameLike( crmPayeeInfo2.getBankAccountNameLike() );
// (全模糊)
crmPayeeInfo.setCreatedByLike( crmPayeeInfo2.getCreatedByLike() );
// (起始日期)
crmPayeeInfo.setCreatedTimeBegin( crmPayeeInfo2.getCreatedTimeBegin() );
// (结束日期)
crmPayeeInfo.setCreatedTimeEnd( crmPayeeInfo2.getCreatedTimeEnd() );
// (格式化)
crmPayeeInfo.setCreatedTimeChar( crmPayeeInfo2.getCreatedTimeChar() );
// (全模糊)
crmPayeeInfo.setUpdatedByLike( crmPayeeInfo2.getUpdatedByLike() );
// (起始日期)
crmPayeeInfo.setUpdatedTimeBegin( crmPayeeInfo2.getUpdatedTimeBegin() );
// (结束日期)
crmPayeeInfo.setUpdatedTimeEnd( crmPayeeInfo2.getUpdatedTimeEnd() );
// (格式化)
crmPayeeInfo.setUpdatedTimeChar( crmPayeeInfo2.getUpdatedTimeChar() );



------------------------------------------------------------------------------------------------------------
 HTML标签区
------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------
  属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键ID：收款人信息ID -->
<input name="payeeInfoId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input name="rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 开户行名称 -->
<input name="openingBank" value="" type="text" maxlength="30"/>
<!-- 银行帐号-收款帐户 -->
<input name="bankAccountCode" value="" type="text" maxlength="30"/>
<!-- 帐户名 -->
<input name="bankAccountName" value="" type="text" maxlength="20"/>
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

<!-- 主键ID：收款人信息ID -->
<input name="crmPayeeInfo.payeeInfoId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input name="crmPayeeInfo.rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 开户行名称 -->
<input name="crmPayeeInfo.openingBank" value="" type="text" maxlength="30"/>
<!-- 银行帐号-收款帐户 -->
<input name="crmPayeeInfo.bankAccountCode" value="" type="text" maxlength="30"/>
<!-- 帐户名 -->
<input name="crmPayeeInfo.bankAccountName" value="" type="text" maxlength="20"/>
<!--  -->
<input name="crmPayeeInfo.createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmPayeeInfo.createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmPayeeInfo.updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmPayeeInfo.updatedTime" value="" type="text" maxlength="36"/>


------------------------------------------------------------------------------------------------------------
  ID + 属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键ID：收款人信息ID -->
<input id="CPI_PAYEE_INFO_ID" name="payeeInfoId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input id="CPI_RF_CUSTOMER_ID" name="rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 开户行名称 -->
<input id="CPI_OPENING_BANK" name="openingBank" value="" type="text" maxlength="30"/>
<!-- 银行帐号-收款帐户 -->
<input id="CPI_BANK_ACCOUNT_CODE" name="bankAccountCode" value="" type="text" maxlength="30"/>
<!-- 帐户名 -->
<input id="CPI_BANK_ACCOUNT_NAME" name="bankAccountName" value="" type="text" maxlength="20"/>
<!--  -->
<input id="CPI_CREATED_BY" name="createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CPI_CREATED_TIME" name="createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CPI_UPDATED_BY" name="updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CPI_UPDATED_TIME" name="updatedTime" value="" type="text" maxlength="36"/>


------------------------------------------------------------------------------------------------------------
  ID + 表名 + 属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键ID：收款人信息ID -->
<input id="CPI_PAYEE_INFO_ID" name="crmPayeeInfo.payeeInfoId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input id="CPI_RF_CUSTOMER_ID" name="crmPayeeInfo.rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 开户行名称 -->
<input id="CPI_OPENING_BANK" name="crmPayeeInfo.openingBank" value="" type="text" maxlength="30"/>
<!-- 银行帐号-收款帐户 -->
<input id="CPI_BANK_ACCOUNT_CODE" name="crmPayeeInfo.bankAccountCode" value="" type="text" maxlength="30"/>
<!-- 帐户名 -->
<input id="CPI_BANK_ACCOUNT_NAME" name="crmPayeeInfo.bankAccountName" value="" type="text" maxlength="20"/>
<!--  -->
<input id="CPI_CREATED_BY" name="crmPayeeInfo.createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CPI_CREATED_TIME" name="crmPayeeInfo.createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CPI_UPDATED_BY" name="crmPayeeInfo.updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CPI_UPDATED_TIME" name="crmPayeeInfo.updatedTime" value="" type="text" maxlength="36"/>




--------------------------------------------------------
 */


    