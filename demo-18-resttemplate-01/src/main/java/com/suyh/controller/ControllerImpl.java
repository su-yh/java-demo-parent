package com.suyh.controller;

import com.suyh.entity.DataBean;
import com.suyh.entity.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/impl")
@Slf4j
public class ControllerImpl {

    /**
     * 构造一个测试数据
     *
     * @return 返回
     */
    public Notice makeNotice() {
        List<DataBean> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataBean data = new DataBean();
            int id = i + 1;
            data.setNoticeCreateTime(new Date());
            data.setNoticeId(id);
            data.setNoticeContent("notice content: " + id);
            data.setNoticeTitle("title: " + id);
            data.setNoticeUpdateTime(new Date());

            dataList.add(data);
        }

        Notice resp = new Notice();
        resp.setData(dataList);
        resp.setMsg("message");
        resp.setStatus(200);

        return resp;
    }

    @GetMapping("/get/info")
    public Notice getInfo() {
        log.info("ControllerImpl getInfo.");

        return makeNotice();
    }

    @GetMapping("/get/info/param")
    public Notice getInfoParam(
            @RequestParam("id") String id,
            @RequestParam("param") String param) {

        log.info("ControllerImpl getInfoParam, id: {}, param: {}", id, param);

        return makeNotice();
    }

    // 这里是get 请求，而参数是一个实体对象。
    // 那么这里就不能使用@RequestParam 注解了。
    @GetMapping("/get/info/data")
    public Notice getInfoData(DataBean reqData) {
        log.info("ControllerImpl getInfoEntity, request param: {}", reqData);

        Notice resp = makeNotice();
        resp.setStatus(200);
        resp.setMsg(reqData.getNoticeContent());
        return resp;
    }
}
