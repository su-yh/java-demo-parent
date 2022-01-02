package com.suyh3402.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * 实现支持: spring.datasource.type 配置项
 * druid 原本没有对该配置项进行支持。
 */
@Configuration
@ConditionalOnMissingBean(DataSource.class)
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@Import(SuyhDataSourceAutoConfiguration.Druid.class)
@Slf4j
public class SuyhDataSourceAutoConfiguration {

    @ConditionalOnClass(DruidDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type",
            havingValue = "com.alibaba.druid.pool.DruidDataSource",
            matchIfMissing = true)
    protected static class Druid {

        @Bean(initMethod = "init")
        @ConfigurationProperties(prefix = "spring.datasource.druid")
        public DataSource dataSource() {
            log.info("Init DruidDataSource");
            return DruidDataSourceBuilder.create().build();
        }
    }
}
