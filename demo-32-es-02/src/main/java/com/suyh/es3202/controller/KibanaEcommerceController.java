package com.suyh.es3202.controller;

import com.suyh.dto.PageInfoDto;
import com.suyh.dto.ResultPage;
import com.suyh.es3202.entity.KibanaEcommerceEntity;
import com.suyh.es3202.service.KibanaEcommerceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@Validated
@Api(value = "KibanaEcommerceController", tags = "KibanaEcommerceController 测试类")
public class KibanaEcommerceController {

    @Resource
    private KibanaEcommerceService kibanaEcommerceService;

    @ApiOperation(value = "按条件分页查询", notes = "按条件分页查询")
    @ApiImplicitParams({})
    @RequestMapping(value = "/suyh/es/kibanaEcommerce/get/entity/List/page", method = RequestMethod.POST)
    public ResultPage<KibanaEcommerceEntity> getPageEntityList(
            @RequestBody KibanaEcommerceEntity processFormEsDo,
            @RequestParam("curPage") int curPage,
            @RequestParam("pageSize") int pageSize) {
        PageInfoDto pageInfo = new PageInfoDto(curPage, pageSize);
        List<KibanaEcommerceEntity> resultList = kibanaEcommerceService.getPageEntityList(
                processFormEsDo, pageInfo);
        return new ResultPage<>(pageInfo, resultList);
    }
}
