package com.suyh.mp.dynamic.config.properties;

import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.suyh.mp.dynamic.constant.DynamicConstants;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 写这个主要是为了处理，原来已经配置了数据源。但是新引入dynamic ，为了不影响以前的配置项而兼容的方案处理。
 * 正常情况，不需要 这样处理的。
 *
 * @author suyh
 * @since 2024-03-19
 */
@ConfigurationProperties(prefix = "suyh.datasource.hikari")
@Data
public class DynamicDataSourceProviderProperties implements DynamicDataSourceProvider {
    @NestedConfigurationProperty
    private HikariDataSource ds001;
    @NestedConfigurationProperty
    private HikariDataSource ds002;
    private Map<String, DataSource> map;

    @Override
    public Map<String, DataSource> loadDataSources() {
        if (map == null) {
            map = new HashMap<>();
            map.put(DynamicConstants.DS001, ds001);
            map.put(DynamicConstants.DS002, ds002);
        }
        return map;
    }
}
