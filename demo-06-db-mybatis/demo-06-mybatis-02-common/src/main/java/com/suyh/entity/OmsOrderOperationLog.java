package com.suyh.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Id;

/**
 * 原始订单操作日志流水实体类
 *
 * @author 苏雲弘
 * @Description:
 * @date 2020-05-07 10:59
 */
@ApiModel(description = "原始订单操作日志流水实体类")
public class OmsOrderOperationLog extends OmsBaseInfo {
    /**
     * 主键ID
     */
    @Id
    @ApiModelProperty(value = "主键ID", name = "operationLogKeyId", example = "operationLogKeyId")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String operationLogKeyId;
    /**
     * 原始订单表中的主键ID
     */
    @ApiModelProperty(value = "原始订单表中的主键ID", name = "rfSourceOrderKeyId", example = "rfSourceOrderKeyId")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String rfSourceOrderKeyId;
    /**
     * 订单来源，来源系统
     */
    @ApiModelProperty(value = "订单来源，来源系统", name = "rfSourceFrom")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String rfSourceFrom;
    /**
     * 内部订单号
     */
    @ApiModelProperty(value = "内部订单号", name = "rfOrderIdInner")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String rfOrderIdInner;
    /**
     * 操作描述
     */
    @ApiModelProperty(value = "操作描述", name = "operationDesc")
    @ColumnType(jdbcType = JdbcType.NVARCHAR)
    private String operationDesc;

    public String getOperationLogKeyId() {
        return operationLogKeyId;
    }

    public void setOperationLogKeyId(String operationLogKeyId) {
        this.operationLogKeyId = operationLogKeyId;
    }

    public String getRfSourceOrderKeyId() {
        return rfSourceOrderKeyId;
    }

    public void setRfSourceOrderKeyId(String rfSourceOrderKeyId) {
        this.rfSourceOrderKeyId = rfSourceOrderKeyId;
    }

    public String getRfSourceFrom() {
        return rfSourceFrom;
    }

    public void setRfSourceFrom(String rfSourceFrom) {
        this.rfSourceFrom = rfSourceFrom;
    }

    public String getRfOrderIdInner() {
        return rfOrderIdInner;
    }

    public void setRfOrderIdInner(String rfOrderIdInner) {
        this.rfOrderIdInner = rfOrderIdInner;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }
}
