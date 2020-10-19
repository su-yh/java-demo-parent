package com.suyh.controller;

import com.suyh.entity.DataBean;
import com.suyh.entity.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public Notice getInfo() throws InterruptedException {

        log.info("ControllerImpl getInfo. before");
        TimeUnit.SECONDS.sleep(3);
        log.info("ControllerImpl getInfo.");

        return makeNotice();
    }

    @PostMapping("/post/info")
    public Notice getInfoPost(int status) throws InterruptedException {

        log.info("ControllerImpl postInfo. before");
        TimeUnit.SECONDS.sleep(3);
        log.info("ControllerImpl postInfo.");

        Notice result = makeNotice();
        result.setStatus(status);
        return result;
    }

    @PostMapping("/post/info/entity")
    public Notice getInfoPostEntity(@RequestBody Notice noticeEntity) throws InterruptedException {

        log.info("ControllerImpl postInfo. before");
        TimeUnit.SECONDS.sleep(3);
        log.info("ControllerImpl postInfo.");

        Notice result = makeNotice();
        result.setStatus(noticeEntity.getStatus());
        return result;
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
