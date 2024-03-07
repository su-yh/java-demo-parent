package com.suyh.demo;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suyh.mapper.CrmContactsInfoMapper;
import com.suyh.model.CrmContactsInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 苏雲弘
 * @Description: 分布查询插件
 * @date 2020-04-16 12:40
 */
@Component
public class DemoPageHelper {
    @Resource
    private CrmContactsInfoMapper crmContactsInfoMapper;

    public void demoPageHelper() {
        int currentPage = 1;    // 当前页，从1 开始计数
        int pageSize = 10;      // 每页条数
        boolean total = true;   // 是否需要记录总条数
        PageHelper.startPage(currentPage, pageSize, total);
        // 返回的list 就是实际获取互的条数，最多为pageSize
        // 这里需要注意，这个框架中间不能有其他的查询语句，否则记录总条数就会取不到。
        // 因为似乎只有第一条查询出来的结果才会是Page<?> 对象实例，而总条数就是Page#getTotal() 方法里记录着。
        // 可以直接转到 Page<?> 再调用getTotal() 方法
        List<CrmContactsInfo> crmContactsInfos = crmContactsInfoMapper.selectAll();// 去查询数据库，不需要在SQL 语句中添加LIMIT 相关的语句
        PageInfo<CrmContactsInfo> info = new PageInfo<>(crmContactsInfos);
        long totalSize = info.getTotal();   // 当前查询条件所获取到的所有数据的总条数。
    }
}
