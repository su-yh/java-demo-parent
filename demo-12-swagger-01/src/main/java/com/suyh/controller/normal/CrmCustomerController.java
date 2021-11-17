package com.suyh.controller.normal;

import com.suyh.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 创建描述：客户、供应商、承运商等明细表控制器
 *
 * @author 苏云弘
 * 创建时间：2020/4/1 10:51:23
 */
// tags: 当前这个类的所有接口都被放到这一个组下，如果方法上面也指定了tags 那就以方法的为准。
@Api(value = "客户、供应商、承运商等明细表控制器", tags = {"客户、供应商、承运商等明细表控制器操作接口"})
@RequestMapping(value = "/CrmCustomer")
public interface CrmCustomerController {


    @ApiOperation(value = "标签显示：添加一个客户、供应商、承运商等",
            notes = "接口描述：增加客户、供应商、承运商等记录")
    // 指定所有的参数列表
    @ApiImplicitParams({
            // 指定具体的参数
            // name: 参数名，这个跟变量名一致
            // value ： 参数的具体意义，作用。相当于添加注释
            // required ： 参数是否必填。
            // dataType ：参数的数据类型。(需要被注解@ApiModel标注的才可以被识别。)
            // allowMultiple: 是否允许多个，当参数是数组、链表的时候就可以使用它了。
            // paramType ：查询参数类型，这里有几种形式：
            //      path 	以URL地址的形式提交数据(@PathVariable 要使用这个注解来获取参数的值)
            //      query 	直接跟参数完成自动映射赋值(@RequestParam)
            //      body 	以流的形式提交 仅支持POST(@RequestBody)
            //      header 	参数在request headers 里边提交(@RequestHeader 从header 中取数据)
            //      form 	以form表单的形式提交 仅支持POST(@RequestParam)
            @ApiImplicitParam(name = "customer", value = "客户详细信息实体类",
                    required = true, paramType = "body", dataType = "CrmCustomer")
    })
    @RequestMapping(value = "/crmCustomerAdd",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmCustomerAdd(@RequestBody CrmCustomer customer);

    // 客户联系人信息  =====================================================================
    @ApiOperation(value = "标签显示：添加一条客户的联系人信息",
            notes = "接口描述：添加客户、供应商、承运商等的联系人记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "crmContactsInfo", value = "客户的联系人信息实体类",
                    required = true, paramType = "body", dataType = "CrmContactsInfo")
    })
    @RequestMapping(value = "/crmContactsAdd",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmContactsAdd(@RequestBody CrmContactsInfo crmContactsInfo);


    @ApiOperation(value = "标签显示：修改客户的联系人信息",
            notes = "接口描述：修改客户、供应商、承运商等的联系人记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "crmContactsInfo", value = "客户的联系人信息实体类",
                    required = true, paramType = "body", dataType = "CrmContactsInfo")
    })
    @RequestMapping(value = "/crmContactsModify",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmContactsModify(@RequestBody CrmContactsInfo crmContactsInfo);


    @ApiOperation(value = "标签显示：删除客户的联系人信息",
            notes = "接口描述：删除客户、供应商、承运商等的联系人记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contactsIds", value = "联系人ID列表",
                    required = true, paramType = "body",
                    allowMultiple = true, dataType = "String")
    })
    @RequestMapping(value = "/crmContactsDel",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmContactsDel(@RequestBody List<String> contactsIds);

    @ApiOperation(value = "标签显示：查询客户的联系人信息列表",
            notes = "接口描述：查询客户的联系人信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户的唯一ID",
                    required = true, dataType = "String")
    })
    @RequestMapping(value = "/crmContactsQuery",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<CrmContactsInfo> crmContactsQuery(String customerId);


    // 客户发票信息  =====================================================================
    @ApiOperation(value = "标签显示：添加一条客户的发票信息",
            notes = "接口描述：添加一条客户的发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "crmInvoiceInfo", value = "客户的发票信息实体类",
                    required = true, paramType = "body", dataType = "CrmInvoiceInfo")
    })
    @RequestMapping(value = "/crmInvoiceAdd",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmInvoiceAdd(@RequestBody CrmInvoiceInfo crmInvoiceInfo);


    @ApiOperation(value = "标签显示：修改客户的发票信息",
            notes = "接口描述：修改客户的发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "crmInvoiceInfo", value = "客户的发票信息实体类",
                    required = true, paramType = "body", dataType = "CrmInvoiceInfo")
    })
    @RequestMapping(value = "/crmInvoiceModify",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmInvoiceModify(@RequestBody CrmInvoiceInfo crmInvoiceInfo);


    @ApiOperation(value = "标签显示：删除客户的发票信息",
            notes = "接口描述：删除客户的发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceIds", value = "发票ID列表",
                    required = true, paramType = "body",
                    allowMultiple = true, dataType = "String")
    })
    @RequestMapping(value = "/crmInvoiceDel",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmInvoiceDel(@RequestBody List<String> invoiceIds);

    @ApiOperation(value = "标签显示：查询客户的发票信息列表",
            notes = "接口描述：查询客户的发票信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户的唯一ID",
                    required = true, dataType = "String")
    })
    @RequestMapping(value = "/crmInvoiceQuery",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<CrmInvoiceInfo> crmInvoiceQuery(String customerId);



    // 客户收款人信息  =====================================================================
    @ApiOperation(value = "标签显示：添加一条客户的收款人信息",
            notes = "接口描述：添加一条客户的收款人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "payeeInfo", value = "客户的收款人信息实体类",
                    required = true, paramType = "body", dataType = "CrmPayeeInfo")
    })
    @RequestMapping(value = "/crmPayeeAdd",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmPayeeAdd(@RequestBody CrmPayeeInfo payeeInfo);


    @ApiOperation(value = "标签显示：修改客户的收款人信息",
            notes = "接口描述：修改客户的收款人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "payeeInfo", value = "客户的收款人信息实体类",
                    required = true, paramType = "body", dataType = "CrmPayeeInfo")
    })
    @RequestMapping(value = "/crmPayeeModify",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmPayeeModify(@RequestBody CrmPayeeInfo payeeInfo);


    @ApiOperation(value = "标签显示：删除客户的收款人信息",
            notes = "接口描述：删除客户的收款人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "payeeIds", value = "收款人ID列表",
                    required = true, paramType = "body",
                    allowMultiple = true, dataType = "String")
    })
    @RequestMapping(value = "/crmPayeeDel",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<String> crmPayeeDel(@RequestBody List<String> payeeIds);

    @ApiOperation(value = "标签显示：查询客户的收款人信息列表",
            notes = "接口描述：查询客户的收款人信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户的唯一ID",
                    required = true, dataType = "String")
    })
    @RequestMapping(value = "/crmPayeeQuery",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResultMode<CrmContactsInfo> crmPayeeQuery(String customerId);
}
