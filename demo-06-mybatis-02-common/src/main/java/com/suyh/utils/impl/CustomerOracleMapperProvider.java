package com.suyh.utils.impl;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.*;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 自定义接口的Mapper 实现，仅支持Oracle。Mysql的需要另外实现
 */
public class CustomerOracleMapperProvider extends MapperTemplate {

    public CustomerOracleMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 过滤(模糊)查询
     *
     * @return
     */
    public SqlNode selectModelByFilterLike(MappedStatement ms) {
        String paramFilter = "filter";

        Class<?> entityClass = getEntityClass(ms);
        setResultType(ms, entityClass);
        String tableName = this.tableName(entityClass);

        String selectAll = SqlHelper.selectAllColumns(entityClass);
        String fromTable = SqlHelper.fromTable(entityClass, tableName);

        List<SqlNode> sqlNodes = new ArrayList<>();
        sqlNodes.add(new StaticTextSqlNode(selectAll + fromTable));
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
        SqlNode ifFilter = makeOracleIfFilterLikeNode(columns, paramFilter);
        WhereSqlNode whereSqlNode = new WhereSqlNode(ms.getConfiguration(), ifFilter);
        sqlNodes.add(whereSqlNode);
        return new MixedSqlNode(sqlNodes);
    }

    /**
     * 过滤(匹配+模糊)查询
     * 可以指定部分字段匹配 + 部分字段模糊查询
     *
     * @return
     */
    public SqlNode selectModelByFilterMatchLike(MappedStatement ms) {
        String paramFilterMatch = "filterMatch";
        String paramFilterLike = "filterLike";

        Class<?> entityClass = getEntityClass(ms);
        setResultType(ms, entityClass);
        String tableName = this.tableName(entityClass);

        String selectAll = SqlHelper.selectAllColumns(entityClass);
        String fromTable = SqlHelper.fromTable(entityClass, tableName);

        List<SqlNode> sqlNodes = new ArrayList<>();
        sqlNodes.add(new StaticTextSqlNode(selectAll + fromTable));
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
        SqlNode ifFilterMatch = CustomBaseMapperProvider.makeIfFilterNode(columns, paramFilterMatch);
        SqlNode ifFilterLike = makeOracleIfFilterLikeNode(columns, paramFilterLike);
        List<SqlNode> ifFilterList = new ArrayList<>();
        ifFilterList.add(ifFilterMatch);
        ifFilterList.add(ifFilterLike);
        MixedSqlNode mixedSqlNodeFilters = new MixedSqlNode(ifFilterList);
        WhereSqlNode whereSqlNode = new WhereSqlNode(ms.getConfiguration(), mixedSqlNodeFilters);
        sqlNodes.add(whereSqlNode);
        return new MixedSqlNode(sqlNodes);
    }
    /**
     * 生成模糊查询过滤条件的 SqlNode
     *
     *      <![CDATA[
     *         <if test="filter != null">
     *           <if test="filter.createdBy != null and filter.createdBy != '' ">
     *             AND created_by LIKE '%' || #{filter.createdBy, jdbcType=NVARCHAR} || '%'
     *           </if>
     *
     *           <if test="filter.createdTimeBefore != null ">
     *             AND created_time >= filter.createTimeBefore
     *           </if>
     *
     *           <if test="filter.createdTimeAfter != null ">
     *             AND created_time < filter.createdTimeAfter
     *           </if>
     *         </if>
     *       ]]>
     *
     * @param columns
     * @param paramFilter
     * @return
     */
    private static SqlNode makeOracleIfFilterLikeNode(
            Set<EntityColumn> columns, String paramFilter) {
        List<SqlNode> ifNodes = new ArrayList<>();
        for (EntityColumn column : columns) {

            if (column.getJavaType().equals(String.class)) {
                // 字符串的模糊匹配
                // AND created_by LIKE '%' || #{filter.createdBy, jdbcType=NVARCHAR} || '%'
                String sqlText = String.format(
                        " AND %s LIKE '%%' || #{%s.%s, jdbcType = %s} || '%%'",
                        column.getColumn(), paramFilter, column.getProperty(),
                        column.getJdbcType().toString());
                StaticTextSqlNode columnNode = new StaticTextSqlNode(sqlText);
                // filter.createdBy != null and filter.createdBy != ''
                String sqlTextString = String.format("%s.%s != null and %s.%s != ''",
                        paramFilter, column.getProperty(), paramFilter, column.getProperty());
                ifNodes.add(new IfSqlNode(columnNode, sqlTextString));
            } else if (column.getJavaType().equals(Date.class)) {
                // 日期类型的区间匹配
                String columnProperty = column.getProperty();
                String propertyBefore = columnProperty + "Before";
                String propertyAfter = columnProperty + "After";

                // 这里不能用  <![CDATA[  ]]> 包含，可能内部已经这样处理了。所以加上会报错
                // <![CDATA[ AND created_by >= #{filter.createdByBefore, jdbcType=TIMESTAMP} ]]>
                String sqlTextBefore = String.format(
                        " AND %s >= #{%s.%s, jdbcType = %s}",
                        column.getColumn(), paramFilter, propertyBefore,
                        column.getJdbcType().toString());
                StaticTextSqlNode columnNodeBefore = new StaticTextSqlNode(sqlTextBefore);
                // filter.createdByBefore != null
                String sqlTextStringBefore = String.format(
                        "%s.%s != null", paramFilter, propertyBefore);
                ifNodes.add(new IfSqlNode(columnNodeBefore, sqlTextStringBefore));

                // <![CDATA[ AND created_by < #{filter.createdByAfter, jdbcType=TIMESTAMP} ]]>
                String sqlTextAfter = String.format(
                        " AND %s < #{%s.%s, jdbcType = %s}",
                        column.getColumn(), paramFilter, propertyAfter,
                        column.getJdbcType().toString());
                StaticTextSqlNode columnNodeAfter = new StaticTextSqlNode(sqlTextAfter);
                // filter.createdByAfter != null
                String sqlTextStringAfter = String.format(
                        "%s.%s != null", paramFilter, propertyAfter);
                ifNodes.add(new IfSqlNode(columnNodeAfter, sqlTextStringAfter));
            } else {
                // 其他数据的精确匹配
                // AND SettleDay = #{filter.SettleDay, jdbcType=NUMERIC}
                String sqlText = String.format(" AND %s = #{%s.%s, jdbcType = %s}",
                        column.getColumn(), paramFilter, column.getProperty(),
                        column.getJdbcType().toString());

                StaticTextSqlNode columnNode = new StaticTextSqlNode(sqlText);

                // filter.createdBy != null
                String sqlTextOther = String.format("%s.%s != null",
                        paramFilter, column.getProperty());
                ifNodes.add(new IfSqlNode(columnNode, sqlTextOther));
            }
        }

        IfSqlNode ifFilter = new IfSqlNode(new MixedSqlNode(ifNodes),
                paramFilter + " != null");
        return ifFilter;
    }
}
