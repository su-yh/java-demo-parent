package com.suyh;

import com.suyh.entity.OmsOrderOperationLog;
import com.suyh.mapper.OmsOrderOperationLogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-05-07 11:36
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = CommonMapperApplication.class)
public class OmsOrderOperationLogMapperTest {
    @Resource
    private OmsOrderOperationLogRepository mapper;

    @Test
    public void test01() {
        String uuid = UUID.randomUUID().toString();
        OmsOrderOperationLog omsOrderOperationLog = new OmsOrderOperationLog();
        omsOrderOperationLog.setOperationLogKeyId(uuid);
        int res = mapper.insert(omsOrderOperationLog);
        System.out.println(res);
    }

    @Test
    public void test02() {
        List<OmsOrderOperationLog> omsOrderOperationLogs = mapper.selectAllSuyh();
        System.out.println(omsOrderOperationLogs);
    }
}
