package com.suyh.utils.impl;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.*;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 这个类没有实际意义，只用来做笔记查看注释作用。
 */
public class BaseMapperProvider extends MapperTemplate {
    public BaseMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    // 到底可不可以在这一个类中实现多个接口方法，可以的，现在不就是多个了吗。
    public SqlNode selectModelByFilter(MappedStatement ms) {
        // 首先获取了实体类型，然后通过setResultType将返回值类型改为entityClass，
        // 就相当于resultType=entityClass。
        Class<?> entityClass = getEntityClass(ms);

        // 修改返回值类型为实体类型
        // 这里为什么要修改呢？因为默认返回值是T，
        // Java并不会自动处理成我们的实体类，默认情况下是Object，
        // 对于所有的查询来说，我们都需要手动设置返回值类型。
        // 对于insert,update,delete来说，这些操作的返回值都是int，所以不需要修改返回结果类型。
        setResultType(ms, entityClass);

        String tableName = this.tableName(entityClass);

        // 结合官方与我的示例
        StaticTextSqlNode staticTextSqlNode = new StaticTextSqlNode(
                "SELECT " + SqlHelper.getAllColumns(entityClass)
                        + " FROM " + tableName);
        return staticTextSqlNode;
    }

    /**
     * 在这里有一点要求，那就是BaseMapperProvider处理BaseMapper<T>中的方法时，
     * 方法名必须一样，因为这里需要通过反射来获取对应的方法，方法名一致一方面是为了减少开发人员的配置，
     * 另一方面和接口对应看起来更清晰。
     * <p>
     * 除了方法名必须一样外，入参必须是MappedStatement ms，除此之外返回值可以是void或者SqlNode之一。
     *
     * @param ms
     * @return
     */
    public SqlNode selectPage(MappedStatement ms) {
        return selectPageSuccess(ms);
    }

    private SqlNode selectPageSuccess(MappedStatement ms) {
        // 首先获取了实体类型，然后通过setResultType将返回值类型改为entityClass，
        // 就相当于resultType=entityClass。
        Class<?> entityClass = getEntityClass(ms);

        // 修改返回值类型为实体类型
        // 这里为什么要修改呢？因为默认返回值是T，
        // Java并不会自动处理成我们的实体类，默认情况下是Object，
        // 对于所有的查询来说，我们都需要手动设置返回值类型。
        // 对于insert,update,delete来说，这些操作的返回值都是int，所以不需要修改返回结果类型。
        setResultType(ms, entityClass);

        String tableName = this.tableName(entityClass);

        List<SqlNode> sqlNodes = new ArrayList<>();
        // 静态的sql部分:select column ... from table
        // 在EntityHelper.getSelectColumns(entityClass)中还处理了别名的情况。
        sqlNodes.add(new StaticTextSqlNode(
                //  "SELECT " + EntityHelper.getSelectColumns(entityClass)
                "SELECT " + SqlHelper.getAllColumns(entityClass)
                        + " FROM " + tableName));
        // 获取全部列
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
        List<SqlNode> ifNodes = new ArrayList<>();
        boolean first = true;
        // 对所有列循环，生成<if test="property!=null">[AND] column = #{property}</if>
        // 这一段使用属性时用的是 entity. + 属性名，entity来自哪儿？
        // 来自我们前面接口定义处的Param("entity")注解，后面的两个分页参数也是。
        for (EntityColumn column : columns) {
            // SetSqlNode

            // [AND] column = #{property}
            String sqlText = String.format("%s%s = #{entity.%s}",
                    (first ? "" : " AND "), column.getColumn(), column.getProperty());
//            String strSource = (first ? "" : " AND ") + column.getColumn()
//                    + " = #{entity." + column.getProperty() + "} ";

            StaticTextSqlNode columnNode = new StaticTextSqlNode(sqlText);
            if (column.getJavaType().equals(String.class)) {
                // 字符串类型的要判断
                // property != null and property != ''
                String sqlTextString = String.format("entity.%s != null and entity.%s != ''",
                        column.getProperty(), column.getProperty());
//                String strSource = "entity."
//                        + column.getProperty() + " != null and " + "entity."
//                        + column.getProperty() + " != '' ";
                ifNodes.add(new IfSqlNode(columnNode, sqlTextString));
            } else {
                // 其它类型不需要判空
                // property != null
                String sqlTextOther = String.format("entity.%s != null",
                        column.getProperty());
                ifNodes.add(new IfSqlNode(columnNode, sqlTextOther));
            }
            first = false;
        }
        // 将if添加到<where>
        WhereSqlNode whereSqlNode = new WhereSqlNode(ms.getConfiguration(), new MixedSqlNode(ifNodes));

        sqlNodes.add(whereSqlNode);
        // 处理分页
//        sqlNodes.add(new IfSqlNode(new StaticTextSqlNode(" LIMIT #{limit}"), "offset==0"));
//        sqlNodes.add(new IfSqlNode(new StaticTextSqlNode(" LIMIT #{limit} OFFSET #{offset} "), "offset>0"));
        return new MixedSqlNode(sqlNodes);
    }

    private SqlNode selectPageFailed(MappedStatement ms) {
        // 首先获取了实体类型，然后通过setResultType将返回值类型改为entityClass，
        // 就相当于resultType=entityClass。
        Class<?> entityClass = getEntityClass(ms);

        // 修改返回值类型为实体类型
        // 这里为什么要修改呢？因为默认返回值是T，
        // Java并不会自动处理成我们的实体类，默认情况下是Object，
        // 对于所有的查询来说，我们都需要手动设置返回值类型。
        // 对于insert,update,delete来说，这些操作的返回值都是int，所以不需要修改返回结果类型。
        setResultType(ms, entityClass);

        List<SqlNode> sqlNodes = new ArrayList<>();
        // 静态的sql部分:select column ... from table
        // 在EntityHelper.getSelectColumns(entityClass)中还处理了别名的情况。
        sqlNodes.add(new StaticTextSqlNode(
                "SELECT " + EntityHelper.getSelectColumns(entityClass)
                        + " FROM " + tableName(entityClass)));
        // 获取全部列
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
        List<SqlNode> ifNodes = new ArrayList<>();
        boolean first = true;
        // 对所有列循环，生成<if test="property!=null">[AND] column = #{property}</if>
        // 这一段使用属性时用的是 entity. + 属性名，entity来自哪儿？
        // 来自我们前面接口定义处的Param("entity")注解，后面的两个分页参数也是。
        for (EntityColumn column : columns) {
            // SetSqlNode

            // [AND] column = #{property}
            String sqlText = String.format("%s%s = #{entity.%s}",
                    (first ? "" : " AND "), column.getColumn(), column.getProperty());
//            String strSource = (first ? "" : " AND ") + column.getColumn()
//                    + " = #{entity." + column.getProperty() + "} ";

            StaticTextSqlNode columnNode = new StaticTextSqlNode(sqlText);
            if (column.getJavaType().equals(String.class)) {
                // 字符串类型的要判断
                // property != null and property != ''
                String sqlTextString = String.format("entity.%s != null and entity.%s != ''",
                        column.getProperty(), column.getProperty());
//                String strSource = "entity."
//                        + column.getProperty() + " != null and " + "entity."
//                        + column.getProperty() + " != '' ";
                ifNodes.add(new IfSqlNode(columnNode, sqlTextString));
            } else {
                // 其它类型不需要判空
                // property != null
                String sqlTextOther = String.format("entity.%s != null",
                        column.getProperty());
                ifNodes.add(new IfSqlNode(columnNode, sqlTextOther));
            }
            first = false;
        }
        // 将if添加到<where>
        WhereSqlNode whereSqlNode = new WhereSqlNode(ms.getConfiguration(), new MixedSqlNode(ifNodes));

        sqlNodes.add(whereSqlNode);
        // 处理分页
//        sqlNodes.add(new IfSqlNode(new StaticTextSqlNode(" LIMIT #{limit}"), "offset==0"));
//        sqlNodes.add(new IfSqlNode(new StaticTextSqlNode(" LIMIT #{limit} OFFSET #{offset} "), "offset>0"));
        return new MixedSqlNode(sqlNodes);
    }
}
