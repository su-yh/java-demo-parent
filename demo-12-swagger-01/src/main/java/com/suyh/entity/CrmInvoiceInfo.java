package com.suyh.entity;


import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @实体名称 
 * @数表名称 CRM_INVOICE_INFO
 * @开发日期 2020-04-03
 * @技术服务 www.fwjava.com
 */
@ApiModel
public class CrmInvoiceInfo implements Serializable {

    /**
     * 主键ID：发票信息ID(必填项)(主键ID)
     */
    private String invoiceInfoId              = null;
    /**
     * 属主ID，映射到客户基本信息表(必填项)
     */
    private String rfCustomerId               = null;
    /**
     * 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】
     */
    private String rfInvoiceTypeId            = null;
    /**
     * 受票方名称
     */
    private String draweeName                 = null;
    /**
     * 统一社会信息代码
     */
    private String unifiedSocialCreditCode    = null;
    /**
     * 开户行名称
     */
    private String openingBank                = null;
    /**
     * 注册电话
     */
    private String registerPhone              = null;
    /**
     * 银行帐号
     */
    private String bankAccountCode            = null;
    /**
     * 帐户名
     */
    private String bankAccountName            = null;
    /**
     * 注册地址
     */
    private String registerAddress            = null;
    /**
     * 
     */
    private String createdBy                  = null;
    /**
     * 
     */
    private Date createdTime                  = null;
    /**
     * 
     */
    private String updatedBy                  = null;
    /**
     * 
     */
    private Date updatedTime                  = null;
    /**
     * 排序
     */
    private String orderBy                    = null;

    /*
     *--------------------------------------------------
     * Getter方法区
     *--------------------------------------------------
     */

    /**
     * 主键ID：发票信息ID(必填项)(主键ID)
     */
    public String getInvoiceInfoId() {
        return trim(invoiceInfoId);
    }
    /**
     * 属主ID，映射到客户基本信息表(必填项)
     */
    public String getRfCustomerId() {
        return trim(rfCustomerId);
    }
    /**
     * 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】
     */
    public String getRfInvoiceTypeId() {
        return trim(rfInvoiceTypeId);
    }
    /**
     * 受票方名称
     */
    public String getDraweeName() {
        return trim(draweeName);
    }
    /**
     * 统一社会信息代码
     */
    public String getUnifiedSocialCreditCode() {
        return trim(unifiedSocialCreditCode);
    }
    /**
     * 开户行名称
     */
    public String getOpeningBank() {
        return trim(openingBank);
    }
    /**
     * 注册电话
     */
    public String getRegisterPhone() {
        return trim(registerPhone);
    }
    /**
     * 银行帐号
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
     * 注册地址
     */
    public String getRegisterAddress() {
        return trim(registerAddress);
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
     * 主键ID：发票信息ID(必填项)(主键ID)
     */
    public void setInvoiceInfoId(String invoiceInfoId) {
        this.invoiceInfoId = invoiceInfoId;
    }
    /**
     * 属主ID，映射到客户基本信息表(必填项)
     */
    public void setRfCustomerId(String rfCustomerId) {
        this.rfCustomerId = rfCustomerId;
    }
    /**
     * 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】
     */
    public void setRfInvoiceTypeId(String rfInvoiceTypeId) {
        this.rfInvoiceTypeId = rfInvoiceTypeId;
    }
    /**
     * 受票方名称
     */
    public void setDraweeName(String draweeName) {
        this.draweeName = draweeName;
    }
    /**
     * 统一社会信息代码
     */
    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }
    /**
     * 开户行名称
     */
    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }
    /**
     * 注册电话
     */
    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }
    /**
     * 银行帐号
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
     * 注册地址
     */
    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
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
     * 主键ID：发票信息ID(全模糊)
     */
    private String invoiceInfoIdLike                 = null;
    /**
     * 属主ID，映射到客户基本信息表(全模糊)
     */
    private String rfCustomerIdLike                  = null;
    /**
     * 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】(全模糊)
     */
    private String rfInvoiceTypeIdLike               = null;
    /**
     * 受票方名称(全模糊)
     */
    private String draweeNameLike                    = null;
    /**
     * 统一社会信息代码(全模糊)
     */
    private String unifiedSocialCreditCodeLike       = null;
    /**
     * 开户行名称(全模糊)
     */
    private String openingBankLike                   = null;
    /**
     * 注册电话(全模糊)
     */
    private String registerPhoneLike                 = null;
    /**
     * 银行帐号(全模糊)
     */
    private String bankAccountCodeLike               = null;
    /**
     * 帐户名(全模糊)
     */
    private String bankAccountNameLike               = null;
    /**
     * 注册地址(全模糊)
     */
    private String registerAddressLike               = null;
    /**
     * (全模糊)
     */
    private String createdByLike                     = null;
    /**
     * (起始日期)
     */
    private String createdTimeBegin                  = null;
    /**
     * (结束日期)
     */
    private String createdTimeEnd                    = null;
    /**
     * (全模糊)
     */
    private String updatedByLike                     = null;
    /**
     * (起始日期)
     */
    private String updatedTimeBegin                  = null;
    /**
     * (结束日期)
     */
    private String updatedTimeEnd                    = null;

    /**
     * 主键ID：发票信息ID(全模糊)
     */
    public String getInvoiceInfoIdLike() {
        return trim(invoiceInfoIdLike);
    }
    public void setInvoiceInfoIdLike(String invoiceInfoIdLike) {
        this.invoiceInfoIdLike = invoiceInfoIdLike;
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
     * 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】(全模糊)
     */
    public String getRfInvoiceTypeIdLike() {
        return trim(rfInvoiceTypeIdLike);
    }
    public void setRfInvoiceTypeIdLike(String rfInvoiceTypeIdLike) {
        this.rfInvoiceTypeIdLike = rfInvoiceTypeIdLike;
    }
    /**
     * 受票方名称(全模糊)
     */
    public String getDraweeNameLike() {
        return trim(draweeNameLike);
    }
    public void setDraweeNameLike(String draweeNameLike) {
        this.draweeNameLike = draweeNameLike;
    }
    /**
     * 统一社会信息代码(全模糊)
     */
    public String getUnifiedSocialCreditCodeLike() {
        return trim(unifiedSocialCreditCodeLike);
    }
    public void setUnifiedSocialCreditCodeLike(String unifiedSocialCreditCodeLike) {
        this.unifiedSocialCreditCodeLike = unifiedSocialCreditCodeLike;
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
     * 注册电话(全模糊)
     */
    public String getRegisterPhoneLike() {
        return trim(registerPhoneLike);
    }
    public void setRegisterPhoneLike(String registerPhoneLike) {
        this.registerPhoneLike = registerPhoneLike;
    }
    /**
     * 银行帐号(全模糊)
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
     * 注册地址(全模糊)
     */
    public String getRegisterAddressLike() {
        return trim(registerAddressLike);
    }
    public void setRegisterAddressLike(String registerAddressLike) {
        this.registerAddressLike = registerAddressLike;
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
CrmInvoiceInfo crmInvoiceInfo = new CrmInvoiceInfo();

// 主键ID：发票信息ID(必填项)(主键ID)
crmInvoiceInfo.setInvoiceInfoId(  );
// 属主ID，映射到客户基本信息表(必填项)
crmInvoiceInfo.setRfCustomerId(  );
// 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】
crmInvoiceInfo.setRfInvoiceTypeId(  );
// 受票方名称
crmInvoiceInfo.setDraweeName(  );
// 统一社会信息代码
crmInvoiceInfo.setUnifiedSocialCreditCode(  );
// 开户行名称
crmInvoiceInfo.setOpeningBank(  );
// 注册电话
crmInvoiceInfo.setRegisterPhone(  );
// 银行帐号
crmInvoiceInfo.setBankAccountCode(  );
// 帐户名
crmInvoiceInfo.setBankAccountName(  );
// 注册地址
crmInvoiceInfo.setRegisterAddress(  );
// 
crmInvoiceInfo.setCreatedBy(  );
// 
crmInvoiceInfo.setCreatedTime(  );
// 
crmInvoiceInfo.setUpdatedBy(  );
// 
crmInvoiceInfo.setUpdatedTime(  );


//------ 自定义部分 ------

// 主键ID：发票信息ID(全模糊)
crmInvoiceInfo.setInvoiceInfoIdLike(  );
// 属主ID，映射到客户基本信息表(全模糊)
crmInvoiceInfo.setRfCustomerIdLike(  );
// 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】(全模糊)
crmInvoiceInfo.setRfInvoiceTypeIdLike(  );
// 受票方名称(全模糊)
crmInvoiceInfo.setDraweeNameLike(  );
// 统一社会信息代码(全模糊)
crmInvoiceInfo.setUnifiedSocialCreditCodeLike(  );
// 开户行名称(全模糊)
crmInvoiceInfo.setOpeningBankLike(  );
// 注册电话(全模糊)
crmInvoiceInfo.setRegisterPhoneLike(  );
// 银行帐号(全模糊)
crmInvoiceInfo.setBankAccountCodeLike(  );
// 帐户名(全模糊)
crmInvoiceInfo.setBankAccountNameLike(  );
// 注册地址(全模糊)
crmInvoiceInfo.setRegisterAddressLike(  );
// (全模糊)
crmInvoiceInfo.setCreatedByLike(  );
// (起始日期)
crmInvoiceInfo.setCreatedTimeBegin(  );
// (结束日期)
crmInvoiceInfo.setCreatedTimeEnd(  );
// (格式化)
crmInvoiceInfo.setCreatedTimeChar(  );
// (全模糊)
crmInvoiceInfo.setUpdatedByLike(  );
// (起始日期)
crmInvoiceInfo.setUpdatedTimeBegin(  );
// (结束日期)
crmInvoiceInfo.setUpdatedTimeEnd(  );
// (格式化)
crmInvoiceInfo.setUpdatedTimeChar(  );


------------------------------------------------------------------------------------------------------------
  Getter方法
------------------------------------------------------------------------------------------------------------

// 
CrmInvoiceInfo crmInvoiceInfo = new CrmInvoiceInfo();

// 主键ID：发票信息ID(必填项)(主键ID)
crmInvoiceInfo.getInvoiceInfoId();
// 属主ID，映射到客户基本信息表(必填项)
crmInvoiceInfo.getRfCustomerId();
// 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】
crmInvoiceInfo.getRfInvoiceTypeId();
// 受票方名称
crmInvoiceInfo.getDraweeName();
// 统一社会信息代码
crmInvoiceInfo.getUnifiedSocialCreditCode();
// 开户行名称
crmInvoiceInfo.getOpeningBank();
// 注册电话
crmInvoiceInfo.getRegisterPhone();
// 银行帐号
crmInvoiceInfo.getBankAccountCode();
// 帐户名
crmInvoiceInfo.getBankAccountName();
// 注册地址
crmInvoiceInfo.getRegisterAddress();
// 
crmInvoiceInfo.getCreatedBy();
// 
crmInvoiceInfo.getCreatedTime();
// 
crmInvoiceInfo.getUpdatedBy();
// 
crmInvoiceInfo.getUpdatedTime();


//------ 自定义部分 ------

// 主键ID：发票信息ID(全模糊)
crmInvoiceInfo.getInvoiceInfoIdLike();
// 属主ID，映射到客户基本信息表(全模糊)
crmInvoiceInfo.getRfCustomerIdLike();
// 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】(全模糊)
crmInvoiceInfo.getRfInvoiceTypeIdLike();
// 受票方名称(全模糊)
crmInvoiceInfo.getDraweeNameLike();
// 统一社会信息代码(全模糊)
crmInvoiceInfo.getUnifiedSocialCreditCodeLike();
// 开户行名称(全模糊)
crmInvoiceInfo.getOpeningBankLike();
// 注册电话(全模糊)
crmInvoiceInfo.getRegisterPhoneLike();
// 银行帐号(全模糊)
crmInvoiceInfo.getBankAccountCodeLike();
// 帐户名(全模糊)
crmInvoiceInfo.getBankAccountNameLike();
// 注册地址(全模糊)
crmInvoiceInfo.getRegisterAddressLike();
// (全模糊)
crmInvoiceInfo.getCreatedByLike();
// (起始日期)
crmInvoiceInfo.getCreatedTimeBegin();
// (结束日期)
crmInvoiceInfo.getCreatedTimeEnd();
// (格式化)
crmInvoiceInfo.getCreatedTimeChar();
// (全模糊)
crmInvoiceInfo.getUpdatedByLike();
// (起始日期)
crmInvoiceInfo.getUpdatedTimeBegin();
// (结束日期)
crmInvoiceInfo.getUpdatedTimeEnd();
// (格式化)
crmInvoiceInfo.getUpdatedTimeChar();


------------------------------------------------------------------------------------------------------------
  Getter Setter方法
------------------------------------------------------------------------------------------------------------

// 
CrmInvoiceInfo crmInvoiceInfo = new CrmInvoiceInfo();

// 主键ID：发票信息ID(必填项)(主键ID)
crmInvoiceInfo.setInvoiceInfoId( crmInvoiceInfo2.getInvoiceInfoId() );
// 属主ID，映射到客户基本信息表(必填项)
crmInvoiceInfo.setRfCustomerId( crmInvoiceInfo2.getRfCustomerId() );
// 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】
crmInvoiceInfo.setRfInvoiceTypeId( crmInvoiceInfo2.getRfInvoiceTypeId() );
// 受票方名称
crmInvoiceInfo.setDraweeName( crmInvoiceInfo2.getDraweeName() );
// 统一社会信息代码
crmInvoiceInfo.setUnifiedSocialCreditCode( crmInvoiceInfo2.getUnifiedSocialCreditCode() );
// 开户行名称
crmInvoiceInfo.setOpeningBank( crmInvoiceInfo2.getOpeningBank() );
// 注册电话
crmInvoiceInfo.setRegisterPhone( crmInvoiceInfo2.getRegisterPhone() );
// 银行帐号
crmInvoiceInfo.setBankAccountCode( crmInvoiceInfo2.getBankAccountCode() );
// 帐户名
crmInvoiceInfo.setBankAccountName( crmInvoiceInfo2.getBankAccountName() );
// 注册地址
crmInvoiceInfo.setRegisterAddress( crmInvoiceInfo2.getRegisterAddress() );
// 
crmInvoiceInfo.setCreatedBy( crmInvoiceInfo2.getCreatedBy() );
// 
crmInvoiceInfo.setCreatedTime( crmInvoiceInfo2.getCreatedTime() );
// 
crmInvoiceInfo.setUpdatedBy( crmInvoiceInfo2.getUpdatedBy() );
// 
crmInvoiceInfo.setUpdatedTime( crmInvoiceInfo2.getUpdatedTime() );


//------ 自定义部分 ------

// 主键ID：发票信息ID(全模糊)
crmInvoiceInfo.setInvoiceInfoIdLike( crmInvoiceInfo2.getInvoiceInfoIdLike() );
// 属主ID，映射到客户基本信息表(全模糊)
crmInvoiceInfo.setRfCustomerIdLike( crmInvoiceInfo2.getRfCustomerIdLike() );
// 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】(全模糊)
crmInvoiceInfo.setRfInvoiceTypeIdLike( crmInvoiceInfo2.getRfInvoiceTypeIdLike() );
// 受票方名称(全模糊)
crmInvoiceInfo.setDraweeNameLike( crmInvoiceInfo2.getDraweeNameLike() );
// 统一社会信息代码(全模糊)
crmInvoiceInfo.setUnifiedSocialCreditCodeLike( crmInvoiceInfo2.getUnifiedSocialCreditCodeLike() );
// 开户行名称(全模糊)
crmInvoiceInfo.setOpeningBankLike( crmInvoiceInfo2.getOpeningBankLike() );
// 注册电话(全模糊)
crmInvoiceInfo.setRegisterPhoneLike( crmInvoiceInfo2.getRegisterPhoneLike() );
// 银行帐号(全模糊)
crmInvoiceInfo.setBankAccountCodeLike( crmInvoiceInfo2.getBankAccountCodeLike() );
// 帐户名(全模糊)
crmInvoiceInfo.setBankAccountNameLike( crmInvoiceInfo2.getBankAccountNameLike() );
// 注册地址(全模糊)
crmInvoiceInfo.setRegisterAddressLike( crmInvoiceInfo2.getRegisterAddressLike() );
// (全模糊)
crmInvoiceInfo.setCreatedByLike( crmInvoiceInfo2.getCreatedByLike() );
// (起始日期)
crmInvoiceInfo.setCreatedTimeBegin( crmInvoiceInfo2.getCreatedTimeBegin() );
// (结束日期)
crmInvoiceInfo.setCreatedTimeEnd( crmInvoiceInfo2.getCreatedTimeEnd() );
// (格式化)
crmInvoiceInfo.setCreatedTimeChar( crmInvoiceInfo2.getCreatedTimeChar() );
// (全模糊)
crmInvoiceInfo.setUpdatedByLike( crmInvoiceInfo2.getUpdatedByLike() );
// (起始日期)
crmInvoiceInfo.setUpdatedTimeBegin( crmInvoiceInfo2.getUpdatedTimeBegin() );
// (结束日期)
crmInvoiceInfo.setUpdatedTimeEnd( crmInvoiceInfo2.getUpdatedTimeEnd() );
// (格式化)
crmInvoiceInfo.setUpdatedTimeChar( crmInvoiceInfo2.getUpdatedTimeChar() );



------------------------------------------------------------------------------------------------------------
 HTML标签区
------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------
  属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键ID：发票信息ID -->
<input name="invoiceInfoId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input name="rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】 -->
<input name="rfInvoiceTypeId" value="" type="text" maxlength="3"/>
<!-- 受票方名称 -->
<input name="draweeName" value="" type="text" maxlength="30"/>
<!-- 统一社会信息代码 -->
<input name="unifiedSocialCreditCode" value="" type="text" maxlength="30"/>
<!-- 开户行名称 -->
<input name="openingBank" value="" type="text" maxlength="30"/>
<!-- 注册电话 -->
<input name="registerPhone" value="" type="text" maxlength="30"/>
<!-- 银行帐号 -->
<input name="bankAccountCode" value="" type="text" maxlength="30"/>
<!-- 帐户名 -->
<input name="bankAccountName" value="" type="text" maxlength="20"/>
<!-- 注册地址 -->
<input name="registerAddress" value="" type="text" maxlength="128"/>
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

<!-- 主键ID：发票信息ID -->
<input name="crmInvoiceInfo.invoiceInfoId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input name="crmInvoiceInfo.rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】 -->
<input name="crmInvoiceInfo.rfInvoiceTypeId" value="" type="text" maxlength="3"/>
<!-- 受票方名称 -->
<input name="crmInvoiceInfo.draweeName" value="" type="text" maxlength="30"/>
<!-- 统一社会信息代码 -->
<input name="crmInvoiceInfo.unifiedSocialCreditCode" value="" type="text" maxlength="30"/>
<!-- 开户行名称 -->
<input name="crmInvoiceInfo.openingBank" value="" type="text" maxlength="30"/>
<!-- 注册电话 -->
<input name="crmInvoiceInfo.registerPhone" value="" type="text" maxlength="30"/>
<!-- 银行帐号 -->
<input name="crmInvoiceInfo.bankAccountCode" value="" type="text" maxlength="30"/>
<!-- 帐户名 -->
<input name="crmInvoiceInfo.bankAccountName" value="" type="text" maxlength="20"/>
<!-- 注册地址 -->
<input name="crmInvoiceInfo.registerAddress" value="" type="text" maxlength="128"/>
<!--  -->
<input name="crmInvoiceInfo.createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmInvoiceInfo.createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmInvoiceInfo.updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmInvoiceInfo.updatedTime" value="" type="text" maxlength="36"/>


------------------------------------------------------------------------------------------------------------
  ID + 属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键ID：发票信息ID -->
<input id="CII_INVOICE_INFO_ID" name="invoiceInfoId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input id="CII_RF_CUSTOMER_ID" name="rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】 -->
<input id="CII_RF_INVOICE_TYPE_ID" name="rfInvoiceTypeId" value="" type="text" maxlength="3"/>
<!-- 受票方名称 -->
<input id="CII_DRAWEE_NAME" name="draweeName" value="" type="text" maxlength="30"/>
<!-- 统一社会信息代码 -->
<input id="CII_UNIFIED_SOCIAL_CREDIT_CODE" name="unifiedSocialCreditCode" value="" type="text" maxlength="30"/>
<!-- 开户行名称 -->
<input id="CII_OPENING_BANK" name="openingBank" value="" type="text" maxlength="30"/>
<!-- 注册电话 -->
<input id="CII_REGISTER_PHONE" name="registerPhone" value="" type="text" maxlength="30"/>
<!-- 银行帐号 -->
<input id="CII_BANK_ACCOUNT_CODE" name="bankAccountCode" value="" type="text" maxlength="30"/>
<!-- 帐户名 -->
<input id="CII_BANK_ACCOUNT_NAME" name="bankAccountName" value="" type="text" maxlength="20"/>
<!-- 注册地址 -->
<input id="CII_REGISTER_ADDRESS" name="registerAddress" value="" type="text" maxlength="128"/>
<!--  -->
<input id="CII_CREATED_BY" name="createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CII_CREATED_TIME" name="createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CII_UPDATED_BY" name="updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CII_UPDATED_TIME" name="updatedTime" value="" type="text" maxlength="36"/>


------------------------------------------------------------------------------------------------------------
  ID + 表名 + 属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键ID：发票信息ID -->
<input id="CII_INVOICE_INFO_ID" name="crmInvoiceInfo.invoiceInfoId" value="" type="text" maxlength="36"/>
<!-- 属主ID，映射到客户基本信息表 -->
<input id="CII_RF_CUSTOMER_ID" name="crmInvoiceInfo.rfCustomerId" value="" type="text" maxlength="36"/>
<!-- 发票类型【SELECT:1-增值税普通发票（电子）,2-增值税普通发票（纸制）3-增值税专用发票,...(其他)】 -->
<input id="CII_RF_INVOICE_TYPE_ID" name="crmInvoiceInfo.rfInvoiceTypeId" value="" type="text" maxlength="3"/>
<!-- 受票方名称 -->
<input id="CII_DRAWEE_NAME" name="crmInvoiceInfo.draweeName" value="" type="text" maxlength="30"/>
<!-- 统一社会信息代码 -->
<input id="CII_UNIFIED_SOCIAL_CREDIT_CODE" name="crmInvoiceInfo.unifiedSocialCreditCode" value="" type="text" maxlength="30"/>
<!-- 开户行名称 -->
<input id="CII_OPENING_BANK" name="crmInvoiceInfo.openingBank" value="" type="text" maxlength="30"/>
<!-- 注册电话 -->
<input id="CII_REGISTER_PHONE" name="crmInvoiceInfo.registerPhone" value="" type="text" maxlength="30"/>
<!-- 银行帐号 -->
<input id="CII_BANK_ACCOUNT_CODE" name="crmInvoiceInfo.bankAccountCode" value="" type="text" maxlength="30"/>
<!-- 帐户名 -->
<input id="CII_BANK_ACCOUNT_NAME" name="crmInvoiceInfo.bankAccountName" value="" type="text" maxlength="20"/>
<!-- 注册地址 -->
<input id="CII_REGISTER_ADDRESS" name="crmInvoiceInfo.registerAddress" value="" type="text" maxlength="128"/>
<!--  -->
<input id="CII_CREATED_BY" name="crmInvoiceInfo.createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CII_CREATED_TIME" name="crmInvoiceInfo.createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CII_UPDATED_BY" name="crmInvoiceInfo.updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CII_UPDATED_TIME" name="crmInvoiceInfo.updatedTime" value="" type="text" maxlength="36"/>




--------------------------------------------------------
 */


    