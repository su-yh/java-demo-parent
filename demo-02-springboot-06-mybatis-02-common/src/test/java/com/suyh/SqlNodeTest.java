package com.suyh;

import org.apache.ibatis.scripting.xmltags.*;
import org.junit.Test;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-05-05 18:22
 */
public class SqlNodeTest {

    @Test
    public void test01() {


        List<SqlNode> sqlNodes = new ArrayList<>();
        // 静态的sql部分:select column ... from table
        // 在EntityHelper.getSelectColumns(entityClass)中还处理了别名的情况。
        sqlNodes.add(new StaticTextSqlNode("SELECT * FROM tableName"));
        // 获取全部列

        System.out.println(sqlNodes.toString());
        SqlNode sqlNode = sqlNodes.get(0);
        System.out.println(sqlNode.toString());
        MixedSqlNode mixedSqlNode = new MixedSqlNode(sqlNodes);
        System.out.println(mixedSqlNode);
    }
}
