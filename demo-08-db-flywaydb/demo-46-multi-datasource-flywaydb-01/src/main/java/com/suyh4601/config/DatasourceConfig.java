package com.suyh4601.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {
    @Configuration
    @ConditionalOnClass(DruidDataSource.class)
    @ConditionalOnProperty(name = "multi4601.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
    public static class Druid {
        /**
         * 使用druid 提供的builder 构造
         * 这里使用的注释{@link ConfigurationProperties} 可以绑定配置属性
         * 在bean 对象仓库之后会处理配置项，所以这些配置会被绑定到DruidDataSource。
         */
        @Bean("dataSourceMaster")
        @ConfigurationProperties(prefix = "multi4601.datasource.druid.master")
        public DruidDataSource masterDruidDataSource() {
            return DruidDataSourceBuilder.create().build();
        }

        // 使用spring 提供的builder 构造
        @Bean("dataSourceSlave")
        @ConfigurationProperties(prefix = "multi4601.datasource.druid.slave")
        public DruidDataSource slaveDruidDataSource() {
            return DataSourceBuilder.create().type(DruidDataSource.class).build();
        }
    }

    @Configuration
    @ConditionalOnProperty(name = "multi4601.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource")
    public static class Hikari {
        @Bean("dataSourceMaster")
        @ConfigurationProperties(prefix = "multi4601.datasource.hikari.master")
        public HikariDataSource masterDruidDataSource() {
            return DataSourceBuilder.create().type(HikariDataSource.class).build();
        }

        @Bean("dataSourceSlave")
        @ConfigurationProperties(prefix = "multi4601.datasource.hikari.slave")
        public HikariDataSource slaveDruidDataSource() {
            return DataSourceBuilder.create().type(HikariDataSource.class).build();
        }
    }
}
