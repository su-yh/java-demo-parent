package com.suyh.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 客户基本信息表结构：内部客户、供应商、承运商
 * 
 * @table: CRM_CUSTOMER_INFO
 * @author: suyh
 * @date: 2020-05-03 11:58:55
 */
@ApiModel(value = "客户基本信息表结构：内部客户、供应商、承运商")
public class CrmCustomerInfo extends BaseModel {

    /**
     * Column: CUSTOMER_ID
     *   主键：客户ID
     */
    @Id
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "主键：客户ID")
    private String customerId;

    /**
     * Column: FULL_NAME
     *   客户全称
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "客户全称", example = "客户全称")
    private String fullName;

    /**
     * Column: SHORT_NAME
     *   客户简称
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "客户简称")
    private String shortName;

    /**
     * Column: MANAGER_NAME
     *   负责人
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "负责人")
    private String managerName;

    /**
     * Column: MANAGER_PHONE
     *   联系电话
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "联系电话")
    private String managerPhone;

    /**
     * Column: INNER_CUSTOMER
     *   是否内部用户【radio:Y/N】
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "是否内部用户【radio:Y/N】")
    private String innerCustomer;

    /**
     * Column: CUSTOMER_TYPE
     *   客户类型【select:1-内部客户,2-供应商,3-承运商】
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "客户类型【select:1-内部客户,2-供应商,3-承运商】")
    private String customerType;

    /**
     * Column: ADDRESS
     *   联系地址
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "联系地址")
    private String address;

    /**
     * Column: US_PLAT_SYSTEM
     *   是否使用平台系统【radio:1-是,2-否】
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "是否使用平台系统【radio:1-是,2-否】")
    private String usPlatSystem;

    /**
     * Column: PLATFORM_ADMIN_USER
     *   平台管理员帐号，非自有系统用户才会有。
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "平台管理员帐号，非自有系统用户才会有。")
    private String platformAdminUser;

    /**
     * Column: SETTLE_DAY
     *   月结日，以0 表示自然月
     */
    @ColumnType(jdbcType = JdbcType.NUMERIC)
    @ApiModelProperty(value = "月结日，以0 表示自然月")
    private BigDecimal settleDay;

    /**
     * Column: PRICE
     *   价格，10 位长度，两位小数位
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "价格，10 位长度，两位小数位")
    private BigDecimal price;

    /**
     * Column: USING_STATUS
     *   启用状态【radio:1-是(启用),2-否(停用)】
     */
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    @ApiModelProperty(value = "启用状态【radio:1-是(启用),2-否(停用)】")
    private BigDecimal usingStatus;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone == null ? null : managerPhone.trim();
    }

    public String getInnerCustomer() {
        return innerCustomer;
    }

    public void setInnerCustomer(String innerCustomer) {
        this.innerCustomer = innerCustomer == null ? null : innerCustomer.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUsPlatSystem() {
        return usPlatSystem;
    }

    public void setUsPlatSystem(String usPlatSystem) {
        this.usPlatSystem = usPlatSystem == null ? null : usPlatSystem.trim();
    }

    public String getPlatformAdminUser() {
        return platformAdminUser;
    }

    public void setPlatformAdminUser(String platformAdminUser) {
        this.platformAdminUser = platformAdminUser == null ? null : platformAdminUser.trim();
    }

    public BigDecimal getSettleDay() {
        return settleDay;
    }

    public void setSettleDay(BigDecimal settleDay) {
        this.settleDay = settleDay;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getUsingStatus() {
        return usingStatus;
    }

    public void setUsingStatus(BigDecimal usingStatus) {
        this.usingStatus = usingStatus;
    }

    @Override
    public String toString() {
        return "CrmCustomerInfo{" +
                super.toString() + ',' + ' ' +
                "customerId='" + customerId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                ", innerCustomer='" + innerCustomer + '\'' +
                ", customerType='" + customerType + '\'' +
                ", address='" + address + '\'' +
                ", usPlatSystem='" + usPlatSystem + '\'' +
                ", platformAdminUser='" + platformAdminUser + '\'' +
                ", settleDay=" + settleDay +
                ", price=" + price +
                ", usingStatus=" + usingStatus +
                '}';
    }
}