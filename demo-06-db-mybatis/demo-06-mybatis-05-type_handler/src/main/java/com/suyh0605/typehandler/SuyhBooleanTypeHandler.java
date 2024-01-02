package com.suyh0605.typehandler;

import org.apache.ibatis.type.BooleanTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 将boolean 类型转换成字符串存到数据库中，而不使用默认的0 或者 1 来处理。
 * 这样更方便查看。
 * 当然了，必须要是数据库类型是varchar 类型的才行。
 *
 * @author suyh
 * @since 2023-08-29
 */
public class SuyhBooleanTypeHandler extends BooleanTypeHandler {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == JdbcType.VARCHAR) {
            ps.setString(i, parameter.toString());
        } else {
            super.setNonNullParameter(ps, i, parameter, jdbcType);
        }
    }
}
