package com.suyh.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * oms 出库单 实体类
 *
 * 创建者 huyong
 * 创建时间 2020/4/23 16:01
 * @param
 * @return
*/
@ApiModel(description ="仓库-出库单 实体类")
public class WmsCkOmsShipmentMO implements Serializable {

    private static final long serialVersionUID = 8578778982771931317L;

    @ApiModelProperty(value = "订单号", name ="orderNo")
    private String orderNo;

    @ApiModelProperty(value = "订单类型", name ="orderType")
    private String orderType;

    @ApiModelProperty(value = "优先级", name ="priority")
    private String priority;

    @ApiModelProperty(value = "货主编号", name ="ownerCargoNo")
    private String ownerCargoNo;

    @ApiModelProperty(value = "货主名称", name ="ownerCargoName")
    private String ownerCargoName;

    @ApiModelProperty(value = "预计发货日期", name ="estimateDate")
    private Date estimateDate;

    @ApiModelProperty(value = "要求交货时间", name ="askDate")
    private Date askDate;

    @ApiModelProperty(value = "是否齐货发货【select:0-是,1-否】", name ="isUnified")
    private String isUnified;

    @ApiModelProperty(value = "仓库号", name ="warehouseNo")
    private String warehouseNo;

    @ApiModelProperty(value = "外部单号", name ="exteriorNoOne")
    private String exteriorNoOne;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getOwnerCargoNo() {
        return ownerCargoNo;
    }

    public void setOwnerCargoNo(String ownerCargoNo) {
        this.ownerCargoNo = ownerCargoNo;
    }

    public String getOwnerCargoName() {
        return ownerCargoName;
    }

    public void setOwnerCargoName(String ownerCargoName) {
        this.ownerCargoName = ownerCargoName;
    }

    public Date getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(Date estimateDate) {
        this.estimateDate = estimateDate;
    }

    public Date getAskDate() {
        return askDate;
    }

    public void setAskDate(Date askDate) {
        this.askDate = askDate;
    }

    public String getIsUnified() {
        return isUnified;
    }

    public void setIsUnified(String isUnified) {
        this.isUnified = isUnified;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getExteriorNoOne() {
        return exteriorNoOne;
    }

    public void setExteriorNoOne(String exteriorNoOne) {
        this.exteriorNoOne = exteriorNoOne;
    }

}
