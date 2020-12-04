package com.suyh.es3202.controller;

import com.suyh.dto.PageInfoDto;
import com.suyh.dto.ResultCollection;
import com.suyh.dto.ResultSingle;
import com.suyh.dto.ResultPage;
import com.suyh.es3202.entity.ProcessFormEsDo;
import com.suyh.es3202.service.EsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 苏雲弘
 * @since 2020-11-26
 */
@RestController
@Api(value = "EsController", tags = "EsController 测试类")
@Slf4j
public class EsController {

    @Resource
    private EsService esService;

    @ApiOperation(value = "测试controller 的一个接口", notes = "测试controller 的一个接口")
    @RequestMapping(value = "/suyh/es/get/info", method = RequestMethod.GET)
    public String getInfo() {
        return "OK";
    }

    @ApiOperation(value = "获取所有实体对象", notes = "获取所有实体对象")
    @ApiImplicitParams({})
    @RequestMapping(value = "/suyh/es/get/entity/List", method = RequestMethod.GET)
    public ResultCollection<ProcessFormEsDo> getEntityList() {
        List<ProcessFormEsDo> resultList = esService.getEntityList();
        return new ResultCollection<>(resultList);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiImplicitParams({})
    @RequestMapping(value = "/suyh/es/get/List/page", method = RequestMethod.GET)
    public ResultPage<ProcessFormEsDo> getPageList(@RequestParam("curPage") int curPage,
            @RequestParam("pageSize") int pageSize) {
        PageInfoDto pageInfo = new PageInfoDto(curPage, pageSize);
        List<ProcessFormEsDo> resultList = esService.getPageList(pageInfo);
        return new ResultPage<>(pageInfo, resultList);
    }

    @ApiOperation(value = "按条件分页查询", notes = "按条件分页查询")
    @ApiImplicitParams({})
    @RequestMapping(value = "/suyh/es/get/entity/List/page", method = RequestMethod.POST)
    public ResultPage<ProcessFormEsDo> getPageEntityList(
            @RequestBody ProcessFormEsDo processFormEsDo,
            @RequestParam("curPage") int curPage,
            @RequestParam("pageSize") int pageSize) {
        PageInfoDto pageInfo = new PageInfoDto(curPage, pageSize);
        List<ProcessFormEsDo> resultList = esService.getPageEntityList(processFormEsDo, pageInfo);
        return new ResultPage<>(pageInfo, resultList);
    }

    @ApiOperation(value = "添加一个实体对象", notes = "添加一个实体对象")
    // 指定所有的参数列表
    @ApiImplicitParams({})
    @RequestMapping(value = "/suyh/es/add/entity", method = RequestMethod.POST)
    public ResultSingle<String> addEntity() {
        String id = esService.addEntity();
        return new ResultSingle<>(id);
    }

    @ApiOperation(value = "删除一个实体对象", notes = "删除一个实体对象")
    // 指定所有的参数列表
    @ApiImplicitParams({})
    @RequestMapping(value = "/suyh/es/del/entity", method = RequestMethod.POST)
    public ResultSingle<String> delEntity(String id) {
        esService.delEntity(id);
        return new ResultSingle<>("成功");
    }

    @ApiOperation(value = "查询一个索引是否存在", notes = "查询一个索引是否存在")
    // 指定所有的参数列表
    @ApiImplicitParams({})
    @RequestMapping(value = "/suyh/es/index/exists", method = RequestMethod.GET)
    public ResultSingle<Boolean> indexExists(@RequestParam("name") String name) {
        boolean result = esService.indexExists(name);
        return new ResultSingle<>(result);
    }

    @ApiOperation(value = "按条件查询删除", notes = "按条件查询删除")
    // 指定所有的参数列表
    @ApiImplicitParams({})
    @RequestMapping(value = "/suyh/es/delByQuery", method = RequestMethod.GET)
    public ResultSingle<Boolean> batchDel() {
        esService.batchDel();
        return new ResultSingle<>(true);
    }

}
