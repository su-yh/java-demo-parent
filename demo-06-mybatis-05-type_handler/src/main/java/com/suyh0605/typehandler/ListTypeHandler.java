package com.suyh0605.typehandler;

import com.suyh0605.util.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author suyh
 * @since 2023-12-02
 */
public class ListTypeHandler<E extends Enum<E>> extends BaseTypeHandler<List<E>> {
    private final Class<E> type;

    public ListTypeHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<E> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JsonUtil.serializable(parameter));
    }

    @Override
    public List<E> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.wasNull() ? null : JsonUtil.deserializeToList02(rs.getString(columnName), type);
    }

    @Override
    public List<E> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.wasNull() ? null : JsonUtil.deserializeToList02(rs.getString(columnIndex), type);
    }

    @Override
    public List<E> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.wasNull() ? null : JsonUtil.deserializeToList02(cs.getString(columnIndex), type);
    }
}
