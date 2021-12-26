package com.suyh4601.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    @Bean("dataSourceMaster")
    @ConfigurationProperties(prefix = "multi4601.datasource.druid.master")
    @ConditionalOnProperty(name = "multi.datasource.druid.enabled", matchIfMissing = true, havingValue = "true")
    public DataSource masterDruidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dataSourceSlave")
    @ConfigurationProperties(prefix = "multi4601.datasource.druid.slave")
    @ConditionalOnProperty(name = "multi.datasource.druid.enabled", matchIfMissing = true, havingValue = "true")
    public DataSource slaveDruidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dataSourceMaster")
    @ConfigurationProperties(prefix = "multi4601.datasource.master")
    @ConditionalOnProperty(name = "multi.datasource.druid.enabled", matchIfMissing = true, havingValue = "false")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dataSourceSlave")
    @ConfigurationProperties(prefix = "multi4601.datasource.slave")
    @ConditionalOnProperty(name = "multi.datasource.druid.enabled", matchIfMissing = true, havingValue = "false")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

}
