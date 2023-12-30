package com.suyh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * 结算信息公共表结构，客户、供应商、承运商。一条结算信息只对应一个属主客户
 * 
 * @table: CRM_SETTLE_MONEY_INFO
 * @author: suyh
 * @date: 2020-05-03 11:58:55
 */
@ApiModel(value = "结算信息公共表结构，客户、供应商、承运商。一条结算信息只对应一个属主客户")
public class CrmSettleMoneyInfo extends BaseModel {
    /**
     * Column: CUSTOMER_ID
     *   主键：映射到客户基础信息表客户ID
     */
    @ApiModelProperty(value = "主键：映射到客户基础信息表客户ID")
    private String customerId;

    /**
     * Column: SETTLE_TYPE
     *   结算方式
     */
    @ApiModelProperty(value = "结算方式")
    private String settleType;

    /**
     * Column: SETTLE_DAY
     *   月结日，以0 表示自然月
     */
    @ApiModelProperty(value = "月结日，以0 表示自然月")
    private BigDecimal settleDay;

    /**
     * Column: TAX_RATE
     *   开票税点(%)
     */
    @ApiModelProperty(value = "开票税点(%)")
    private BigDecimal taxRate;

    /**
     * Column: ENABLE_DELAY_DAYS
     *   允许延期天数
     */
    @ApiModelProperty(value = "允许延期天数")
    private BigDecimal enableDelayDays;

    /**
     * Column: MAX_ARREARS_MONEY
     *   欠款上限(元)
     */
    @ApiModelProperty(value = "欠款上限(元)")
    private BigDecimal maxArrearsMoney;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType == null ? null : settleType.trim();
    }

    public BigDecimal getSettleDay() {
        return settleDay;
    }

    public void setSettleDay(BigDecimal settleDay) {
        this.settleDay = settleDay;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getEnableDelayDays() {
        return enableDelayDays;
    }

    public void setEnableDelayDays(BigDecimal enableDelayDays) {
        this.enableDelayDays = enableDelayDays;
    }

    public BigDecimal getMaxArrearsMoney() {
        return maxArrearsMoney;
    }

    public void setMaxArrearsMoney(BigDecimal maxArrearsMoney) {
        this.maxArrearsMoney = maxArrearsMoney;
    }
}