package com.suyh0605.typehandler.list;

import com.suyh0605.util.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author suyh
 * @since 2023-12-02
 */
public abstract class ListTypeHandler<E extends Enum<E>> extends BaseTypeHandler<List<E>> {
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
        String value = rs.getString(columnName);
        if (!StringUtils.hasText(value)) {
            return null;
        }

        return JsonUtil.deserializeToList02(value, type);
    }

    @Override
    public List<E> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (!StringUtils.hasText(value)) {
            return null;
        }
        return JsonUtil.deserializeToList02(value, type);
    }

    @Override
    public List<E> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (!StringUtils.hasText(value)) {
            return null;
        }
        return JsonUtil.deserializeToList02(value, type);
    }
}
