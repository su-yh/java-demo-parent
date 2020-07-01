package com.suyh.mapper;

import com.suyh.MybatisDemoApplication;
import com.suyh.model.CrmCustomerInfo;
import com.suyh.model.CrmCustomerInfoFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = MybatisDemoApplication.class)
public class TestCrmCustomerInfoMapper {
    @Resource
    private CrmCustomerInfoMapper customerInfoMapper;

    @Test
    public void testInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = new Date();
        String nowTimeString = sdf.format(nowTime);

        CrmCustomerInfo record = new CrmCustomerInfo();
        record.setCustomerId(UUID.randomUUID().toString());
        record.setCreatedBy("苏云弘-" + nowTimeString);
        record.setCreatedTime(nowTime);
        record.setUpdatedBy("苏雲弘-" + nowTimeString);
        record.setUpdatedTime(nowTime);
        int res = customerInfoMapper.insert(record);
        System.out.println("uuid: " + record.getCustomerId() + ", res: " + res);
    }

    @Test
    public void testQueryAll() {
        List<CrmCustomerInfo> crmCustomerInfos = customerInfoMapper.selectAll();
        System.out.println("all: " + crmCustomerInfos);
    }

    @Test
    public void testQueryByFilter() {
        CrmCustomerInfo filter = new CrmCustomerInfo();
        filter.setCreatedBy("苏云弘-2020-05-03 11:49:23");
        List<CrmCustomerInfo> crmCustomerInfos
                = customerInfoMapper.selectModelByFilter(filter);

        System.out.println("filter query: " + crmCustomerInfos);
    }

    @Test
    public void testUpdasteByFilter() {
        CrmCustomerInfo model = new CrmCustomerInfo();
        model.setCreatedBy("苏云弘");
        CrmCustomerInfo filter = new CrmCustomerInfo();
        filter.setCreatedBy("苏云弘-2020-05-03 11:49:23");
        int res = customerInfoMapper.updateModelByFilter(model, filter);
        System.out.println("update by filter, res: " + res);
    }

    @Test
    public void testQueryLike() throws ParseException {
        CrmCustomerInfoFilter filterLike = new CrmCustomerInfoFilter();
        filterLike.setCreatedBy("苏云弘");
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        Date before = sdf.parse("2020-05-03 00:00:00");
        Date after = sdf.parse("2020-05-03 11:50:00");
        filterLike.setCreatedTimeBefore(before);
        filterLike.setCreatedTimeAfter(after);
        List<CrmCustomerInfo> crmCustomerInfos
                = customerInfoMapper.selectModelByFilterLike(filterLike);
        System.out.println("query like: " + crmCustomerInfos);
    }

}
