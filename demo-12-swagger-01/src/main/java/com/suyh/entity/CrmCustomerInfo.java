package com.suyh.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @实体名称 
 * @数表名称 CRM_CUSTOMER_INFO
 * @开发日期 2020-04-03
 * @技术服务 www.fwjava.com
 */
@ApiModel
public class CrmCustomerInfo implements Serializable {

    /**
     * 主键：客户ID(必填项)(主键ID)
     */
    @ApiModelProperty(value = "客户ID", example = "uuid")
    private String customerId           = null;
    /**
     * 客户全称(必填项)
     */
    @ApiModelProperty(value = "客户全名", example = "中华人民共和国")
    private String fullName             = null;
    /**
     * 客户简称
     */
    @ApiModelProperty(value = "客户简称", example = "中国")
    private String shortName            = null;
    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人", example = "小李子")
    private String managerName          = null;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "电话", example = "71489894")
    private String managerPhone         = null;
    /**
     * 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(必填项)
     */
    @ApiModelProperty(value = "是否内部用户【1: 是,2: 否】", example = "1")
    private String innerCustomer        = null;
    /**
     * 客户类型【select:1-内部客户,2-供应商,3-承运商】
     */
    private String customerType         = null;
    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址", name = "address", example = "重庆南川")
    private String address              = null;
    /**
     * 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】
     */
    private String usPlatSystem         = null;
    /**
     * 平台管理员帐号，非自有系统用户才会有。
     */
    private String platformAdminUser    = null;
    /**
     * 启用状态【radio:1-是(启用),2-否(停用)】(必填项)
     */
    private String usingStatus          = null;
    /**
     * 结算方式
     */
    private String settleType           = null;
    /**
     * 月结日，以0表示自然月
     */
    private Integer settleDay           = null;
    /**
     * 开票税点(%)
     */
    private String taxRate              = null;
    /**
     * 允许延期天数
     */
    private Integer enableDelayDays     = null;
    /**
     * 欠款上限(元)
     */
    private Integer maxArrearsMoney     = null;
    /**
     * 
     */
    private String createdBy            = null;
    /**
     * 
     */
    private Date createdTime            = null;
    /**
     * 
     */
    private String updatedBy            = null;
    /**
     * 
     */
    private Date updatedTime            = null;
    /**
     * 排序
     */
    private String orderBy              = null;

    /*
     *--------------------------------------------------
     * Getter方法区
     *--------------------------------------------------
     */

    /**
     * 主键：客户ID(必填项)(主键ID)
     */
    public String getCustomerId() {
        return trim(customerId);
    }
    /**
     * 客户全称(必填项)
     */
    public String getFullName() {
        return trim(fullName);
    }
    /**
     * 客户简称
     */
    public String getShortName() {
        return trim(shortName);
    }
    /**
     * 负责人
     */
    public String getManagerName() {
        return trim(managerName);
    }
    /**
     * 联系电话
     */
    public String getManagerPhone() {
        return trim(managerPhone);
    }
    /**
     * 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(必填项)
     */
    public String getInnerCustomer() {
        return trim(innerCustomer);
    }
    /**
     * 客户类型【select:1-内部客户,2-供应商,3-承运商】
     */
    public String getCustomerType() {
        return trim(customerType);
    }
    /**
     * 联系地址
     */
    public String getAddress() {
        return trim(address);
    }
    /**
     * 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】
     */
    public String getUsPlatSystem() {
        return trim(usPlatSystem);
    }
    /**
     * 平台管理员帐号，非自有系统用户才会有。
     */
    public String getPlatformAdminUser() {
        return trim(platformAdminUser);
    }
    /**
     * 启用状态【radio:1-是(启用),2-否(停用)】(必填项)
     */
    public String getUsingStatus() {
        return trim(usingStatus);
    }
    /**
     * 结算方式
     */
    public String getSettleType() {
        return trim(settleType);
    }
    /**
     * 月结日，以0表示自然月
     */
    public Integer getSettleDay() {
        return settleDay;
    }
    /**
     * 开票税点(%)
     */
    public String getTaxRate() {
        return trim(taxRate);
    }
    /**
     * 允许延期天数
     */
    public Integer getEnableDelayDays() {
        return enableDelayDays;
    }
    /**
     * 欠款上限(元)
     */
    public Integer getMaxArrearsMoney() {
        return maxArrearsMoney;
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
     * 主键：客户ID(必填项)(主键ID)
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    /**
     * 客户全称(必填项)
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    /**
     * 客户简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    /**
     * 负责人
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    /**
     * 联系电话
     */
    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }
    /**
     * 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(必填项)
     */
    public void setInnerCustomer(String innerCustomer) {
        this.innerCustomer = innerCustomer;
    }
    /**
     * 客户类型【select:1-内部客户,2-供应商,3-承运商】
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    /**
     * 联系地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】
     */
    public void setUsPlatSystem(String usPlatSystem) {
        this.usPlatSystem = usPlatSystem;
    }
    /**
     * 平台管理员帐号，非自有系统用户才会有。
     */
    public void setPlatformAdminUser(String platformAdminUser) {
        this.platformAdminUser = platformAdminUser;
    }
    /**
     * 启用状态【radio:1-是(启用),2-否(停用)】(必填项)
     */
    public void setUsingStatus(String usingStatus) {
        this.usingStatus = usingStatus;
    }
    /**
     * 结算方式
     */
    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }
    /**
     * 月结日，以0表示自然月
     */
    public void setSettleDay(Integer settleDay) {
        this.settleDay = settleDay;
    }
    /**
     * 开票税点(%)
     */
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }
    /**
     * 允许延期天数
     */
    public void setEnableDelayDays(Integer enableDelayDays) {
        this.enableDelayDays = enableDelayDays;
    }
    /**
     * 欠款上限(元)
     */
    public void setMaxArrearsMoney(Integer maxArrearsMoney) {
        this.maxArrearsMoney = maxArrearsMoney;
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
     * 主键：客户ID(全模糊)
     */
    private String customerIdLike              = null;
    /**
     * 客户全称(全模糊)
     */
    private String fullNameLike                = null;
    /**
     * 客户简称(全模糊)
     */
    private String shortNameLike               = null;
    /**
     * 负责人(全模糊)
     */
    private String managerNameLike             = null;
    /**
     * 联系电话(全模糊)
     */
    private String managerPhoneLike            = null;
    /**
     * 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(全模糊)
     */
    private String innerCustomerLike           = null;
    /**
     * 客户类型【select:1-内部客户,2-供应商,3-承运商】(全模糊)
     */
    private String customerTypeLike            = null;
    /**
     * 联系地址(全模糊)
     */
    private String addressLike                 = null;
    /**
     * 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】(全模糊)
     */
    private String usPlatSystemLike            = null;
    /**
     * 平台管理员帐号，非自有系统用户才会有。(全模糊)
     */
    private String platformAdminUserLike       = null;
    /**
     * 启用状态【radio:1-是(启用),2-否(停用)】(全模糊)
     */
    private String usingStatusLike             = null;
    /**
     * 结算方式(全模糊)
     */
    private String settleTypeLike              = null;
    /**
     * 开票税点(%)(全模糊)
     */
    private String taxRateLike                 = null;
    /**
     * (全模糊)
     */
    private String createdByLike               = null;
    /**
     * (起始日期)
     */
    private String createdTimeBegin            = null;
    /**
     * (结束日期)
     */
    private String createdTimeEnd              = null;
    /**
     * (全模糊)
     */
    private String updatedByLike               = null;
    /**
     * (起始日期)
     */
    private String updatedTimeBegin            = null;
    /**
     * (结束日期)
     */
    private String updatedTimeEnd              = null;

    /**
     * 主键：客户ID(全模糊)
     */
    public String getCustomerIdLike() {
        return trim(customerIdLike);
    }
    public void setCustomerIdLike(String customerIdLike) {
        this.customerIdLike = customerIdLike;
    }
    /**
     * 客户全称(全模糊)
     */
    public String getFullNameLike() {
        return trim(fullNameLike);
    }
    public void setFullNameLike(String fullNameLike) {
        this.fullNameLike = fullNameLike;
    }
    /**
     * 客户简称(全模糊)
     */
    public String getShortNameLike() {
        return trim(shortNameLike);
    }
    public void setShortNameLike(String shortNameLike) {
        this.shortNameLike = shortNameLike;
    }
    /**
     * 负责人(全模糊)
     */
    public String getManagerNameLike() {
        return trim(managerNameLike);
    }
    public void setManagerNameLike(String managerNameLike) {
        this.managerNameLike = managerNameLike;
    }
    /**
     * 联系电话(全模糊)
     */
    public String getManagerPhoneLike() {
        return trim(managerPhoneLike);
    }
    public void setManagerPhoneLike(String managerPhoneLike) {
        this.managerPhoneLike = managerPhoneLike;
    }
    /**
     * 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(全模糊)
     */
    public String getInnerCustomerLike() {
        return trim(innerCustomerLike);
    }
    public void setInnerCustomerLike(String innerCustomerLike) {
        this.innerCustomerLike = innerCustomerLike;
    }
    /**
     * 客户类型【select:1-内部客户,2-供应商,3-承运商】(全模糊)
     */
    public String getCustomerTypeLike() {
        return trim(customerTypeLike);
    }
    public void setCustomerTypeLike(String customerTypeLike) {
        this.customerTypeLike = customerTypeLike;
    }
    /**
     * 联系地址(全模糊)
     */
    public String getAddressLike() {
        return trim(addressLike);
    }
    public void setAddressLike(String addressLike) {
        this.addressLike = addressLike;
    }
    /**
     * 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】(全模糊)
     */
    public String getUsPlatSystemLike() {
        return trim(usPlatSystemLike);
    }
    public void setUsPlatSystemLike(String usPlatSystemLike) {
        this.usPlatSystemLike = usPlatSystemLike;
    }
    /**
     * 平台管理员帐号，非自有系统用户才会有。(全模糊)
     */
    public String getPlatformAdminUserLike() {
        return trim(platformAdminUserLike);
    }
    public void setPlatformAdminUserLike(String platformAdminUserLike) {
        this.platformAdminUserLike = platformAdminUserLike;
    }
    /**
     * 启用状态【radio:1-是(启用),2-否(停用)】(全模糊)
     */
    public String getUsingStatusLike() {
        return trim(usingStatusLike);
    }
    public void setUsingStatusLike(String usingStatusLike) {
        this.usingStatusLike = usingStatusLike;
    }
    /**
     * 结算方式(全模糊)
     */
    public String getSettleTypeLike() {
        return trim(settleTypeLike);
    }
    public void setSettleTypeLike(String settleTypeLike) {
        this.settleTypeLike = settleTypeLike;
    }
    /**
     * 开票税点(%)(全模糊)
     */
    public String getTaxRateLike() {
        return trim(taxRateLike);
    }
    public void setTaxRateLike(String taxRateLike) {
        this.taxRateLike = taxRateLike;
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
CrmCustomerInfo crmCustomerInfo = new CrmCustomerInfo();

// 主键：客户ID(必填项)(主键ID)
crmCustomerInfo.setCustomerId(  );
// 客户全称(必填项)
crmCustomerInfo.setFullName(  );
// 客户简称
crmCustomerInfo.setShortName(  );
// 负责人
crmCustomerInfo.setManagerName(  );
// 联系电话
crmCustomerInfo.setManagerPhone(  );
// 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(必填项)
crmCustomerInfo.setInnerCustomer(  );
// 客户类型【select:1-内部客户,2-供应商,3-承运商】
crmCustomerInfo.setCustomerType(  );
// 联系地址
crmCustomerInfo.setAddress(  );
// 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】
crmCustomerInfo.setUsPlatSystem(  );
// 平台管理员帐号，非自有系统用户才会有。
crmCustomerInfo.setPlatformAdminUser(  );
// 启用状态【radio:1-是(启用),2-否(停用)】(必填项)
crmCustomerInfo.setUsingStatus(  );
// 结算方式
crmCustomerInfo.setSettleType(  );
// 月结日，以0表示自然月
crmCustomerInfo.setSettleDay(  );
// 开票税点(%)
crmCustomerInfo.setTaxRate(  );
// 允许延期天数
crmCustomerInfo.setEnableDelayDays(  );
// 欠款上限(元)
crmCustomerInfo.setMaxArrearsMoney(  );
// 
crmCustomerInfo.setCreatedBy(  );
// 
crmCustomerInfo.setCreatedTime(  );
// 
crmCustomerInfo.setUpdatedBy(  );
// 
crmCustomerInfo.setUpdatedTime(  );


//------ 自定义部分 ------

// 主键：客户ID(全模糊)
crmCustomerInfo.setCustomerIdLike(  );
// 客户全称(全模糊)
crmCustomerInfo.setFullNameLike(  );
// 客户简称(全模糊)
crmCustomerInfo.setShortNameLike(  );
// 负责人(全模糊)
crmCustomerInfo.setManagerNameLike(  );
// 联系电话(全模糊)
crmCustomerInfo.setManagerPhoneLike(  );
// 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(全模糊)
crmCustomerInfo.setInnerCustomerLike(  );
// 客户类型【select:1-内部客户,2-供应商,3-承运商】(全模糊)
crmCustomerInfo.setCustomerTypeLike(  );
// 联系地址(全模糊)
crmCustomerInfo.setAddressLike(  );
// 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】(全模糊)
crmCustomerInfo.setUsPlatSystemLike(  );
// 平台管理员帐号，非自有系统用户才会有。(全模糊)
crmCustomerInfo.setPlatformAdminUserLike(  );
// 启用状态【radio:1-是(启用),2-否(停用)】(全模糊)
crmCustomerInfo.setUsingStatusLike(  );
// 结算方式(全模糊)
crmCustomerInfo.setSettleTypeLike(  );
// 开票税点(%)(全模糊)
crmCustomerInfo.setTaxRateLike(  );
// (全模糊)
crmCustomerInfo.setCreatedByLike(  );
// (起始日期)
crmCustomerInfo.setCreatedTimeBegin(  );
// (结束日期)
crmCustomerInfo.setCreatedTimeEnd(  );
// (格式化)
crmCustomerInfo.setCreatedTimeChar(  );
// (全模糊)
crmCustomerInfo.setUpdatedByLike(  );
// (起始日期)
crmCustomerInfo.setUpdatedTimeBegin(  );
// (结束日期)
crmCustomerInfo.setUpdatedTimeEnd(  );
// (格式化)
crmCustomerInfo.setUpdatedTimeChar(  );


------------------------------------------------------------------------------------------------------------
  Getter方法
------------------------------------------------------------------------------------------------------------

// 
CrmCustomerInfo crmCustomerInfo = new CrmCustomerInfo();

// 主键：客户ID(必填项)(主键ID)
crmCustomerInfo.getCustomerId();
// 客户全称(必填项)
crmCustomerInfo.getFullName();
// 客户简称
crmCustomerInfo.getShortName();
// 负责人
crmCustomerInfo.getManagerName();
// 联系电话
crmCustomerInfo.getManagerPhone();
// 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(必填项)
crmCustomerInfo.getInnerCustomer();
// 客户类型【select:1-内部客户,2-供应商,3-承运商】
crmCustomerInfo.getCustomerType();
// 联系地址
crmCustomerInfo.getAddress();
// 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】
crmCustomerInfo.getUsPlatSystem();
// 平台管理员帐号，非自有系统用户才会有。
crmCustomerInfo.getPlatformAdminUser();
// 启用状态【radio:1-是(启用),2-否(停用)】(必填项)
crmCustomerInfo.getUsingStatus();
// 结算方式
crmCustomerInfo.getSettleType();
// 月结日，以0表示自然月
crmCustomerInfo.getSettleDay();
// 开票税点(%)
crmCustomerInfo.getTaxRate();
// 允许延期天数
crmCustomerInfo.getEnableDelayDays();
// 欠款上限(元)
crmCustomerInfo.getMaxArrearsMoney();
// 
crmCustomerInfo.getCreatedBy();
// 
crmCustomerInfo.getCreatedTime();
// 
crmCustomerInfo.getUpdatedBy();
// 
crmCustomerInfo.getUpdatedTime();


//------ 自定义部分 ------

// 主键：客户ID(全模糊)
crmCustomerInfo.getCustomerIdLike();
// 客户全称(全模糊)
crmCustomerInfo.getFullNameLike();
// 客户简称(全模糊)
crmCustomerInfo.getShortNameLike();
// 负责人(全模糊)
crmCustomerInfo.getManagerNameLike();
// 联系电话(全模糊)
crmCustomerInfo.getManagerPhoneLike();
// 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(全模糊)
crmCustomerInfo.getInnerCustomerLike();
// 客户类型【select:1-内部客户,2-供应商,3-承运商】(全模糊)
crmCustomerInfo.getCustomerTypeLike();
// 联系地址(全模糊)
crmCustomerInfo.getAddressLike();
// 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】(全模糊)
crmCustomerInfo.getUsPlatSystemLike();
// 平台管理员帐号，非自有系统用户才会有。(全模糊)
crmCustomerInfo.getPlatformAdminUserLike();
// 启用状态【radio:1-是(启用),2-否(停用)】(全模糊)
crmCustomerInfo.getUsingStatusLike();
// 结算方式(全模糊)
crmCustomerInfo.getSettleTypeLike();
// 开票税点(%)(全模糊)
crmCustomerInfo.getTaxRateLike();
// (全模糊)
crmCustomerInfo.getCreatedByLike();
// (起始日期)
crmCustomerInfo.getCreatedTimeBegin();
// (结束日期)
crmCustomerInfo.getCreatedTimeEnd();
// (格式化)
crmCustomerInfo.getCreatedTimeChar();
// (全模糊)
crmCustomerInfo.getUpdatedByLike();
// (起始日期)
crmCustomerInfo.getUpdatedTimeBegin();
// (结束日期)
crmCustomerInfo.getUpdatedTimeEnd();
// (格式化)
crmCustomerInfo.getUpdatedTimeChar();


------------------------------------------------------------------------------------------------------------
  Getter Setter方法
------------------------------------------------------------------------------------------------------------

// 
CrmCustomerInfo crmCustomerInfo = new CrmCustomerInfo();

// 主键：客户ID(必填项)(主键ID)
crmCustomerInfo.setCustomerId( crmCustomerInfo2.getCustomerId() );
// 客户全称(必填项)
crmCustomerInfo.setFullName( crmCustomerInfo2.getFullName() );
// 客户简称
crmCustomerInfo.setShortName( crmCustomerInfo2.getShortName() );
// 负责人
crmCustomerInfo.setManagerName( crmCustomerInfo2.getManagerName() );
// 联系电话
crmCustomerInfo.setManagerPhone( crmCustomerInfo2.getManagerPhone() );
// 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(必填项)
crmCustomerInfo.setInnerCustomer( crmCustomerInfo2.getInnerCustomer() );
// 客户类型【select:1-内部客户,2-供应商,3-承运商】
crmCustomerInfo.setCustomerType( crmCustomerInfo2.getCustomerType() );
// 联系地址
crmCustomerInfo.setAddress( crmCustomerInfo2.getAddress() );
// 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】
crmCustomerInfo.setUsPlatSystem( crmCustomerInfo2.getUsPlatSystem() );
// 平台管理员帐号，非自有系统用户才会有。
crmCustomerInfo.setPlatformAdminUser( crmCustomerInfo2.getPlatformAdminUser() );
// 启用状态【radio:1-是(启用),2-否(停用)】(必填项)
crmCustomerInfo.setUsingStatus( crmCustomerInfo2.getUsingStatus() );
// 结算方式
crmCustomerInfo.setSettleType( crmCustomerInfo2.getSettleType() );
// 月结日，以0表示自然月
crmCustomerInfo.setSettleDay( crmCustomerInfo2.getSettleDay() );
// 开票税点(%)
crmCustomerInfo.setTaxRate( crmCustomerInfo2.getTaxRate() );
// 允许延期天数
crmCustomerInfo.setEnableDelayDays( crmCustomerInfo2.getEnableDelayDays() );
// 欠款上限(元)
crmCustomerInfo.setMaxArrearsMoney( crmCustomerInfo2.getMaxArrearsMoney() );
// 
crmCustomerInfo.setCreatedBy( crmCustomerInfo2.getCreatedBy() );
// 
crmCustomerInfo.setCreatedTime( crmCustomerInfo2.getCreatedTime() );
// 
crmCustomerInfo.setUpdatedBy( crmCustomerInfo2.getUpdatedBy() );
// 
crmCustomerInfo.setUpdatedTime( crmCustomerInfo2.getUpdatedTime() );


//------ 自定义部分 ------

// 主键：客户ID(全模糊)
crmCustomerInfo.setCustomerIdLike( crmCustomerInfo2.getCustomerIdLike() );
// 客户全称(全模糊)
crmCustomerInfo.setFullNameLike( crmCustomerInfo2.getFullNameLike() );
// 客户简称(全模糊)
crmCustomerInfo.setShortNameLike( crmCustomerInfo2.getShortNameLike() );
// 负责人(全模糊)
crmCustomerInfo.setManagerNameLike( crmCustomerInfo2.getManagerNameLike() );
// 联系电话(全模糊)
crmCustomerInfo.setManagerPhoneLike( crmCustomerInfo2.getManagerPhoneLike() );
// 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】(全模糊)
crmCustomerInfo.setInnerCustomerLike( crmCustomerInfo2.getInnerCustomerLike() );
// 客户类型【select:1-内部客户,2-供应商,3-承运商】(全模糊)
crmCustomerInfo.setCustomerTypeLike( crmCustomerInfo2.getCustomerTypeLike() );
// 联系地址(全模糊)
crmCustomerInfo.setAddressLike( crmCustomerInfo2.getAddressLike() );
// 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】(全模糊)
crmCustomerInfo.setUsPlatSystemLike( crmCustomerInfo2.getUsPlatSystemLike() );
// 平台管理员帐号，非自有系统用户才会有。(全模糊)
crmCustomerInfo.setPlatformAdminUserLike( crmCustomerInfo2.getPlatformAdminUserLike() );
// 启用状态【radio:1-是(启用),2-否(停用)】(全模糊)
crmCustomerInfo.setUsingStatusLike( crmCustomerInfo2.getUsingStatusLike() );
// 结算方式(全模糊)
crmCustomerInfo.setSettleTypeLike( crmCustomerInfo2.getSettleTypeLike() );
// 开票税点(%)(全模糊)
crmCustomerInfo.setTaxRateLike( crmCustomerInfo2.getTaxRateLike() );
// (全模糊)
crmCustomerInfo.setCreatedByLike( crmCustomerInfo2.getCreatedByLike() );
// (起始日期)
crmCustomerInfo.setCreatedTimeBegin( crmCustomerInfo2.getCreatedTimeBegin() );
// (结束日期)
crmCustomerInfo.setCreatedTimeEnd( crmCustomerInfo2.getCreatedTimeEnd() );
// (格式化)
crmCustomerInfo.setCreatedTimeChar( crmCustomerInfo2.getCreatedTimeChar() );
// (全模糊)
crmCustomerInfo.setUpdatedByLike( crmCustomerInfo2.getUpdatedByLike() );
// (起始日期)
crmCustomerInfo.setUpdatedTimeBegin( crmCustomerInfo2.getUpdatedTimeBegin() );
// (结束日期)
crmCustomerInfo.setUpdatedTimeEnd( crmCustomerInfo2.getUpdatedTimeEnd() );
// (格式化)
crmCustomerInfo.setUpdatedTimeChar( crmCustomerInfo2.getUpdatedTimeChar() );



------------------------------------------------------------------------------------------------------------
 HTML标签区
------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------
  属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键：客户ID -->
<input name="customerId" value="" type="text" maxlength="36"/>
<!-- 客户全称 -->
<input name="fullName" value="" type="text" maxlength="60"/>
<!-- 客户简称 -->
<input name="shortName" value="" type="text" maxlength="30"/>
<!-- 负责人 -->
<input name="managerName" value="" type="text" maxlength="20"/>
<!-- 联系电话 -->
<input name="managerPhone" value="" type="text" maxlength="20"/>
<!-- 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】 -->
<input name="innerCustomer" value="" type="text" maxlength="3"/>
<!-- 客户类型【select:1-内部客户,2-供应商,3-承运商】 -->
<input name="customerType" value="" type="text" maxlength="3"/>
<!-- 联系地址 -->
<input name="address" value="" type="text" maxlength="128"/>
<!-- 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】 -->
<input name="usPlatSystem" value="" type="text" maxlength="3"/>
<!-- 平台管理员帐号，非自有系统用户才会有。 -->
<input name="platformAdminUser" value="" type="text" maxlength="30"/>
<!-- 启用状态【radio:1-是(启用),2-否(停用)】 -->
<input name="usingStatus" value="" type="text" maxlength="3"/>
<!-- 结算方式 -->
<input name="settleType" value="" type="text" maxlength="10"/>
<!-- 月结日，以0表示自然月 -->
<input name="settleDay" value="" type="text" maxlength="10"/>
<!-- 开票税点(%) -->
<input name="taxRate" value="" type="text" maxlength="10"/>
<!-- 允许延期天数 -->
<input name="enableDelayDays" value="" type="text" maxlength="10"/>
<!-- 欠款上限(元) -->
<input name="maxArrearsMoney" value="" type="text" maxlength="10"/>
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

<!-- 主键：客户ID -->
<input name="crmCustomerInfo.customerId" value="" type="text" maxlength="36"/>
<!-- 客户全称 -->
<input name="crmCustomerInfo.fullName" value="" type="text" maxlength="60"/>
<!-- 客户简称 -->
<input name="crmCustomerInfo.shortName" value="" type="text" maxlength="30"/>
<!-- 负责人 -->
<input name="crmCustomerInfo.managerName" value="" type="text" maxlength="20"/>
<!-- 联系电话 -->
<input name="crmCustomerInfo.managerPhone" value="" type="text" maxlength="20"/>
<!-- 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】 -->
<input name="crmCustomerInfo.innerCustomer" value="" type="text" maxlength="3"/>
<!-- 客户类型【select:1-内部客户,2-供应商,3-承运商】 -->
<input name="crmCustomerInfo.customerType" value="" type="text" maxlength="3"/>
<!-- 联系地址 -->
<input name="crmCustomerInfo.address" value="" type="text" maxlength="128"/>
<!-- 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】 -->
<input name="crmCustomerInfo.usPlatSystem" value="" type="text" maxlength="3"/>
<!-- 平台管理员帐号，非自有系统用户才会有。 -->
<input name="crmCustomerInfo.platformAdminUser" value="" type="text" maxlength="30"/>
<!-- 启用状态【radio:1-是(启用),2-否(停用)】 -->
<input name="crmCustomerInfo.usingStatus" value="" type="text" maxlength="3"/>
<!-- 结算方式 -->
<input name="crmCustomerInfo.settleType" value="" type="text" maxlength="10"/>
<!-- 月结日，以0表示自然月 -->
<input name="crmCustomerInfo.settleDay" value="" type="text" maxlength="10"/>
<!-- 开票税点(%) -->
<input name="crmCustomerInfo.taxRate" value="" type="text" maxlength="10"/>
<!-- 允许延期天数 -->
<input name="crmCustomerInfo.enableDelayDays" value="" type="text" maxlength="10"/>
<!-- 欠款上限(元) -->
<input name="crmCustomerInfo.maxArrearsMoney" value="" type="text" maxlength="10"/>
<!--  -->
<input name="crmCustomerInfo.createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmCustomerInfo.createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmCustomerInfo.updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input name="crmCustomerInfo.updatedTime" value="" type="text" maxlength="36"/>


------------------------------------------------------------------------------------------------------------
  ID + 属性区
------------------------------------------------------------------------------------------------------------

<!-- 主键：客户ID -->
<input id="CCI_CUSTOMER_ID" name="customerId" value="" type="text" maxlength="36"/>
<!-- 客户全称 -->
<input id="CCI_FULL_NAME" name="fullName" value="" type="text" maxlength="60"/>
<!-- 客户简称 -->
<input id="CCI_SHORT_NAME" name="shortName" value="" type="text" maxlength="30"/>
<!-- 负责人 -->
<input id="CCI_MANAGER_NAME" name="managerName" value="" type="text" maxlength="20"/>
<!-- 联系电话 -->
<input id="CCI_MANAGER_PHONE" name="managerPhone" value="" type="text" maxlength="20"/>
<!-- 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】 -->
<input id="CCI_INNER_CUSTOMER" name="innerCustomer" value="" type="text" maxlength="3"/>
<!-- 客户类型【select:1-内部客户,2-供应商,3-承运商】 -->
<input id="CCI_CUSTOMER_TYPE" name="customerType" value="" type="text" maxlength="3"/>
<!-- 联系地址 -->
<input id="CCI_ADDRESS" name="address" value="" type="text" maxlength="128"/>
<!-- 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】 -->
<input id="CCI_US_PLAT_SYSTEM" name="usPlatSystem" value="" type="text" maxlength="3"/>
<!-- 平台管理员帐号，非自有系统用户才会有。 -->
<input id="CCI_PLATFORM_ADMIN_USER" name="platformAdminUser" value="" type="text" maxlength="30"/>
<!-- 启用状态【radio:1-是(启用),2-否(停用)】 -->
<input id="CCI_USING_STATUS" name="usingStatus" value="" type="text" maxlength="3"/>
<!-- 结算方式 -->
<input id="CCI_SETTLE_TYPE" name="settleType" value="" type="text" maxlength="10"/>
<!-- 月结日，以0表示自然月 -->
<input id="CCI_SETTLE_DAY" name="settleDay" value="" type="text" maxlength="10"/>
<!-- 开票税点(%) -->
<input id="CCI_TAX_RATE" name="taxRate" value="" type="text" maxlength="10"/>
<!-- 允许延期天数 -->
<input id="CCI_ENABLE_DELAY_DAYS" name="enableDelayDays" value="" type="text" maxlength="10"/>
<!-- 欠款上限(元) -->
<input id="CCI_MAX_ARREARS_MONEY" name="maxArrearsMoney" value="" type="text" maxlength="10"/>
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

<!-- 主键：客户ID -->
<input id="CCI_CUSTOMER_ID" name="crmCustomerInfo.customerId" value="" type="text" maxlength="36"/>
<!-- 客户全称 -->
<input id="CCI_FULL_NAME" name="crmCustomerInfo.fullName" value="" type="text" maxlength="60"/>
<!-- 客户简称 -->
<input id="CCI_SHORT_NAME" name="crmCustomerInfo.shortName" value="" type="text" maxlength="30"/>
<!-- 负责人 -->
<input id="CCI_MANAGER_NAME" name="crmCustomerInfo.managerName" value="" type="text" maxlength="20"/>
<!-- 联系电话 -->
<input id="CCI_MANAGER_PHONE" name="crmCustomerInfo.managerPhone" value="" type="text" maxlength="20"/>
<!-- 是否内部用户(这个字段似乎有些多余了)【radio:1-是,2-否】 -->
<input id="CCI_INNER_CUSTOMER" name="crmCustomerInfo.innerCustomer" value="" type="text" maxlength="3"/>
<!-- 客户类型【select:1-内部客户,2-供应商,3-承运商】 -->
<input id="CCI_CUSTOMER_TYPE" name="crmCustomerInfo.customerType" value="" type="text" maxlength="3"/>
<!-- 联系地址 -->
<input id="CCI_ADDRESS" name="crmCustomerInfo.address" value="" type="text" maxlength="128"/>
<!-- 是否使用平台系统(当有自有系统时则可不使用平台系统)【radio:1-是,2-否】 -->
<input id="CCI_US_PLAT_SYSTEM" name="crmCustomerInfo.usPlatSystem" value="" type="text" maxlength="3"/>
<!-- 平台管理员帐号，非自有系统用户才会有。 -->
<input id="CCI_PLATFORM_ADMIN_USER" name="crmCustomerInfo.platformAdminUser" value="" type="text" maxlength="30"/>
<!-- 启用状态【radio:1-是(启用),2-否(停用)】 -->
<input id="CCI_USING_STATUS" name="crmCustomerInfo.usingStatus" value="" type="text" maxlength="3"/>
<!-- 结算方式 -->
<input id="CCI_SETTLE_TYPE" name="crmCustomerInfo.settleType" value="" type="text" maxlength="10"/>
<!-- 月结日，以0表示自然月 -->
<input id="CCI_SETTLE_DAY" name="crmCustomerInfo.settleDay" value="" type="text" maxlength="10"/>
<!-- 开票税点(%) -->
<input id="CCI_TAX_RATE" name="crmCustomerInfo.taxRate" value="" type="text" maxlength="10"/>
<!-- 允许延期天数 -->
<input id="CCI_ENABLE_DELAY_DAYS" name="crmCustomerInfo.enableDelayDays" value="" type="text" maxlength="10"/>
<!-- 欠款上限(元) -->
<input id="CCI_MAX_ARREARS_MONEY" name="crmCustomerInfo.maxArrearsMoney" value="" type="text" maxlength="10"/>
<!--  -->
<input id="CCI_CREATED_BY" name="crmCustomerInfo.createdBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CCI_CREATED_TIME" name="crmCustomerInfo.createdTime" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CCI_UPDATED_BY" name="crmCustomerInfo.updatedBy" value="" type="text" maxlength="36"/>
<!--  -->
<input id="CCI_UPDATED_TIME" name="crmCustomerInfo.updatedTime" value="" type="text" maxlength="36"/>




--------------------------------------------------------
 */


    