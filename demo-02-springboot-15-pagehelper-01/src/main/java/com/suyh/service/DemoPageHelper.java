package com.suyh.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-04-16 12:40
 */
public class DemoPageHelper {

    public void demoPageHelper() {

        int currentPage = 1;    // 当前页，从1 开始计数
        int pageSize = 10;      // 每页条数
        boolean total = true;   // 是否需要记录总条数
        PageHelper.startPage(currentPage, pageSize, total);
        // 返回的list 就是实际获取互的条数，最多为pageSize
        List<Object> list = func(); // 去查询数据库，不需要在SQL 语句中添加LIMIT 相关的语句
        PageInfo<Object> info = new PageInfo<>(list);
        long totalSize = info.getTotal();   // 当前查询条件所获取到的所有数据的总条数。
    }

    public List<Object> func() {
        return null;
    }
}
