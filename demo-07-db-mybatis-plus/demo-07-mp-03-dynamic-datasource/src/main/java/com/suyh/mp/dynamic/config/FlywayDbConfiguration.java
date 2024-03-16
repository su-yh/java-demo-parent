/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.suyh.mp.dynamic.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@AutoConfigureAfter(value = DynamicDataSourceAutoConfiguration.class)
@Configuration
public class FlywayDbConfiguration {

    // DynamicRoutingDataSource

    @Bean("masterFlyway")
    public FlywayMigrationInitializer masterFlyway(DataSource dataSource) {
        // 参考：DynamicDataSourceAutoConfiguration
        DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) dataSource;

        // 这里的参数 ds "dynamic-002" 来自配置文件中的: spring.datasource.dynamic.datasource.dynamic-001
        DataSource dataSource001 = dynamicRoutingDataSource.getDataSource("dynamic-001");
        FluentConfiguration masterFlywayConfig = new FluentConfiguration();
        masterFlywayConfig.baselineOnMigrate(true)
                .dataSource(dataSource001)
                // mysql 没有schemas 一定不能设置，设置了之后就不会建表了。
                // .schemas("master")
                .locations("db/migration/001")
                .validateOnMigrate(true)
                .ignoreFutureMigrations(false);
        Flyway flyway001 = masterFlywayConfig.load();
        return new FlywayMigrationInitializer(flyway001, null);
    }


    @Bean("slaveFlyway")
    public FlywayMigrationInitializer slaveFlyway(DataSource dataSource) {
        // 参考：DynamicDataSourceAutoConfiguration
        DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) dataSource;

        // 这里的参数 ds "dynamic-002" 来自配置文件中的: spring.datasource.dynamic.datasource.dynamic-002
        DataSource dataSource002 = dynamicRoutingDataSource.getDataSource("dynamic-002");
        FluentConfiguration slaveFlywayConfig = new FluentConfiguration();
        slaveFlywayConfig.baselineOnMigrate(true)
                .dataSource(dataSource002)
                // .schemas("slave")
                .locations("db/migration/002")
                .validateOnMigrate(true)
                .ignoreFutureMigrations(false);

        Flyway slaveFlyway = slaveFlywayConfig.load();
        return new FlywayMigrationInitializer(slaveFlyway, null);
    }
}
