package com.suyh3402.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.suyh3402.datasource.DruidDataSourcePoolMetadata;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration;
import org.springframework.boot.jdbc.DataSourceUnwrapper;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataSourcePoolMetadataProvidersConfiguration.class,
        BeansConfiguration.DruidDataSourcePoolMetadataProviderConfiguration.class})
public class BeansConfiguration {

    @ConditionalOnClass(DruidDataSource.class)
    static class DruidDataSourcePoolMetadataProviderConfiguration {
        @Bean
        DataSourcePoolMetadataProvider druidPoolDataSourceMetadataProvider() {
            return (dataSource) -> {
                DruidDataSource druidDataSource = DataSourceUnwrapper.unwrap(dataSource, DruidDataSource.class);
                if (druidDataSource != null) {
                    return new DruidDataSourcePoolMetadata(druidDataSource);
                }
                return null;
            };
        }
    }
}
