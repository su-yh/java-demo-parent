package com.suyh.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-04-03 14:17
 */
@ApiModel
@Data
public class CrmCustomer implements Serializable {
    private static final long serialVersionUID = -4610881375727920940L;

    /**
     * 客户基础信息
     */
    private CrmCustomerInfo crmCustomerInfo;

    /**
     * 联系人列表
     */
    private List<CrmContactsInfo> contactsInfoList;

    /**
     * 发票信息
     */
    private List<CrmInvoiceInfo> invoiceInfoList;

    /**
     * 收款人信息
     */
    private List<CrmPayeeInfo> payeeInfoList;

    public CrmCustomerInfo getCrmCustomerInfo() {
        return crmCustomerInfo;
    }

    public void setCrmCustomerInfo(CrmCustomerInfo crmCustomerInfo) {
        this.crmCustomerInfo = crmCustomerInfo;
    }

    public List<CrmContactsInfo> getContactsInfoList() {
        return contactsInfoList;
    }

    public void setContactsInfoList(List<CrmContactsInfo> contactsInfoList) {
        this.contactsInfoList = contactsInfoList;
    }

    public List<CrmInvoiceInfo> getInvoiceInfoList() {
        return invoiceInfoList;
    }

    public void setInvoiceInfoList(List<CrmInvoiceInfo> invoiceInfoList) {
        this.invoiceInfoList = invoiceInfoList;
    }

    public List<CrmPayeeInfo> getPayeeInfoList() {
        return payeeInfoList;
    }

    public void setPayeeInfoList(List<CrmPayeeInfo> payeeInfoList) {
        this.payeeInfoList = payeeInfoList;
    }

    @Override
    public String toString() {
        return "CrmCustomer{" +
                "crmCustomerInfo=" + crmCustomerInfo +
                ", contactsInfoList=" + contactsInfoList +
                ", invoiceInfoList=" + invoiceInfoList +
                ", payeeInfoList=" + payeeInfoList +
                '}';
    }
}
