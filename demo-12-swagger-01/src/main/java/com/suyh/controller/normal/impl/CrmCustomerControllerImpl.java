package com.suyh.controller.normal.impl;

import com.suyh.controller.normal.CrmCustomerController;
import com.suyh.entity.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-04-03 17:14
 */
@RestController
public class CrmCustomerControllerImpl implements CrmCustomerController {
    @Override
    public ResultMode<String> crmCustomerAdd(CrmCustomer customer) {
        return null;
    }

    @Override
    public ResultMode<String> crmContactsAdd(CrmContactsInfo crmContactsInfo) {
        return null;
    }

    @Override
    public ResultMode<String> crmContactsModify(CrmContactsInfo crmContactsInfo) {
        return null;
    }

    @Override
    public ResultMode<String> crmContactsDel(List<String> contactsIds) {
        return null;
    }

    @Override
    public ResultMode<CrmContactsInfo> crmContactsQuery(String customerId) {
        return null;
    }

    @Override
    public ResultMode<String> crmInvoiceAdd(CrmInvoiceInfo crmInvoiceInfo) {
        return null;
    }

    @Override
    public ResultMode<String> crmInvoiceModify(CrmInvoiceInfo crmInvoiceInfo) {
        return null;
    }

    @Override
    public ResultMode<String> crmInvoiceDel(List<String> invoiceIds) {
        return null;
    }

    @Override
    public ResultMode<CrmInvoiceInfo> crmInvoiceQuery(String customerId) {
        return null;
    }

    @Override
    public ResultMode<String> crmPayeeAdd(CrmPayeeInfo payeeInfo) {
        return null;
    }

    @Override
    public ResultMode<String> crmPayeeModify(CrmPayeeInfo payeeInfo) {
        return null;
    }

    @Override
    public ResultMode<String> crmPayeeDel(List<String> payeeIds) {
        return null;
    }

    @Override
    public ResultMode<CrmContactsInfo> crmPayeeQuery(String customerId) {
        return null;
    }
}
