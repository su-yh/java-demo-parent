package com.suyh.interceptor;

import com.github.vertical_blank.sqlformatter.SqlFormatter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注册为一个bean 对象就可以生效了。
 * 将SQL 以及对应的查询参数值一起打印出来。
 *
 * @author suyh
 * @since 2024-05-06
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
@Component
public class SqlHandlerInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object proceed = invocation.proceed();
        try {
            CompletableFuture.runAsync(() -> {
                try {
                    extracted(invocation);
                } catch (Exception e) {
                    log.error("Error executing SQL asynchronously", e);
                }
            });
        } catch (Exception exception) {
            log.error("SqlHandler invoke failed", exception);
        }
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // NOP
    }

    private void extracted(Invocation invocation) {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        try (Connection connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            Object parameterObject = args[1];
            Configuration configuration = mappedStatement.getConfiguration();

            // Create PreparedStatement object for SQL query
            try (PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql())) {
                if (!CollectionUtils.isEmpty(parameterMappings)) {
                    for (int i = 0; i < parameterMappings.size(); i++) {
                        ParameterMapping parameterMapping = parameterMappings.get(i);
                        if (parameterMapping.getMode() != ParameterMode.OUT) {
                            String propertyName = parameterMapping.getProperty();
                            Object value;
                            if (boundSql.hasAdditionalParameter(propertyName)) {
                                value = boundSql.getAdditionalParameter(propertyName);
                            } else if (parameterObject == null) {
                                value = null;
                            } else if (configuration.getTypeHandlerRegistry().hasTypeHandler(parameterObject.getClass())) {
                                value = parameterObject;
                            } else {
                                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                                value = metaObject.getValue(propertyName);
                            }
                            TypeHandler typeHandler = parameterMapping.getTypeHandler();
                            JdbcType jdbcType = parameterMapping.getJdbcType();
                            if (value == null && jdbcType == null) {
                                jdbcType = mappedStatement.getConfiguration().getJdbcTypeForNull();
                            }
                            if (Objects.nonNull(value)) {
                                typeHandler.setParameter(preparedStatement, i + 1, value, jdbcType);
                            }
                        }
                    }
                    String formattedSql = SqlFormatter.format(preparedStatement.toString());
                    log.info("Executed SQL Database name: {}, URL: {} ", metaData.getDatabaseProductName(), extractHost(metaData.getURL()));
                    log.info("\n#################### {} \n====================", formattedSql);

                }
            }
        } catch (SQLException e) {
            log.error("SQL execution error", e);
        }
    }

    private static String extractHost(String url) {
        // url = jdbc:mysql://192.168.8.34:3306/suyh-mock-cds?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false
        // 定义主机部分匹配的正则表达式
//        String hostPattern = "(?<=\\/\\/)(.*?)(?=\\?)";   // 结果为：192.168.8.34:3306/suyh-mock-cds
        String hostPattern = "(.*?)(?=\\?)";    // 结果为：jdbc:mysql://192.168.8.34:3306/suyh-mock-cds
        Pattern pattern = Pattern.compile(hostPattern);
        Matcher matcher = pattern.matcher(url);
        return matcher.find() ? matcher.group() : "Unknown Host";
    }
}
