package com.suyh;

import com.suyh.entity.CrmCustomerInfo;
import com.suyh.entity.CrmCustomerInfoFilter;
import com.suyh.mapper.CrmCustomerInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonMapperApplication.class)
public class CrmCustomerInfoMapperTest {


    @Resource
    private CrmCustomerInfoMapper customerInfoMapper;

    @Test
    public void test01() {
        List<CrmCustomerInfo> crmCustomerInfos = customerInfoMapper.selectAll();
        if (crmCustomerInfos == null || crmCustomerInfos.isEmpty()) {
            System.out.println("空数据，没有数据");
            return;
        }
        for (CrmCustomerInfo info : crmCustomerInfos) {
            System.out.println("createBy: " + info.getCreatedBy());
        }
    }

    @Test
    public void test02() {
        List<String> names = customerInfoMapper.selectCreateBy();
        if (names == null || names.isEmpty()) {
            System.out.println("空数据");
            return;
        }

        for (String name : names) {
            System.out.println(name);
        }
    }

    @Test
    public void test03() {
//        CrmCustomerInfo customerInfo = new CrmCustomerInfo();
//        List<CrmCustomerInfo> list = customerInfoMapper.selectPage(customerInfo, 0, 10);
//        if (list == null || list.isEmpty()) {
//            System.out.println("空");
//            return;
//        }
//
//        // 为什么是两个 null
//        for (CrmCustomerInfo info : list) {
//            System.out.println(info);
//        }

    }

    @Test
    public void test04() {
        CrmCustomerInfo customerInfo = new CrmCustomerInfo();
        customerInfo.setCreatedBy("苏云弘");
        List<CrmCustomerInfo> list = customerInfoMapper.selectModelByFilter(null);
        if (list == null || list.isEmpty()) {
            System.out.println("空");
            return;
        }

        for (CrmCustomerInfo info : list) {
            System.out.println(info);
        }

    }

    @Test
    public void test05() {
        CrmCustomerInfo model = new CrmCustomerInfo();
        model.setShortName("雲弘");
        model.setUpdatedTime(new Date());
        CrmCustomerInfoFilter filter = new CrmCustomerInfoFilter();
        filter.setCreatedBy("苏云弘");
        int res = customerInfoMapper.updateModelByFilter(model, filter);
        System.out.println("updateModelByFilter res: " + res);
    }

    @Test
    public void test06() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date updateBefore = sdf.parse("2020-05-08 00:35:39");
        Date updateAfter = sdf.parse("2020-05-08 00:35:49");

        CrmCustomerInfoFilter filter = new CrmCustomerInfoFilter();
        filter.setCreatedBy("云弘");
        filter.setUpdatedTimeBefore(updateBefore);
        filter.setUpdatedTimeAfter(updateAfter);
        List<CrmCustomerInfo> crmCustomerInfos
                = customerInfoMapper.selectModelByFilterLike(filter);
        System.out.println(crmCustomerInfos);
    }

    @Test
    public void test07() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date updateBefore = sdf.parse("2020-05-08 00:35:39");
        Date updateAfter = sdf.parse("2020-05-08 00:35:49");

        CrmCustomerInfoFilter filterMatch = new CrmCustomerInfoFilter();
        filterMatch.setShortName("雲弘");
        CrmCustomerInfoFilter filterLike = new CrmCustomerInfoFilter();
        filterLike.setCreatedBy("云弘");
//        filterLike.setUpdatedTimeBefore(updateBefore);
//        filterLike.setUpdatedTimeAfter(updateAfter);
        List<CrmCustomerInfo> crmCustomerInfos
                = customerInfoMapper.selectModelByFilterMatchLike(filterMatch, filterLike);
        System.out.println(crmCustomerInfos);
    }

    @Test
    public void test107() {
        CrmCustomerInfo crmCustomerInfo = new CrmCustomerInfo();
        crmCustomerInfo.setCustomerId(UUID.randomUUID().toString());
        int res = customerInfoMapper.insert(crmCustomerInfo);
        System.out.println("res: " + res);
    }



    // 还没测试过
    // 这个可以处理复杂的条件判断查询
    /**
     * select 字段列
     * from tablename
     * where (sex = ? and age between ? and ?) or (user_name = ?)
     * order by age desc
     */
    @Test
    public void testSelectByExample() {
        // 创建Example对象，并且指定要操作的实体类的Class对象
        Example example = new Example(CrmCustomerInfo.class);
        // 创建查询条件对象,默认是and关系
        // 查询女性,并且年龄在16到24
        example.createCriteria().andEqualTo("sex", 2)
                .andBetween("age", 16, 24);
        // 添加查询条件，or关系
        example.or(example.createCriteria().andEqualTo("userName", "lisi"));
        // 或者用户名是lisi的
        // 实现排序，多个排序规则以','隔开
        example.setOrderByClause("age desc");
        Example.Criteria criteria = example.createCriteria();
        // criteria.andCustomerIdEqualsTo("aaab");
        List<CrmCustomerInfo> users = customerInfoMapper.selectByExample(example);
        for (CrmCustomerInfo user : users) {
            System.out.println(user);
        }
    }

}
