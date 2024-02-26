/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.suyh4601.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayDbConfiguration {

    @Bean("masterFlyway")
    public FlywayMigrationInitializer masterFlyway(@Qualifier("dataSourceMaster") DataSource dataSourceMaster) {
        FluentConfiguration masterFlywayConfig = new FluentConfiguration();
        masterFlywayConfig.baselineOnMigrate(true)
                .dataSource(dataSourceMaster)
                // mysql 没有schemas 一定不能设置，设置了之后就不会建表了。
                // .schemas("master")
                .locations("db/migrate/master")
                .validateOnMigrate(true)
                .ignoreFutureMigrations(false);
        Flyway masterFlyway = masterFlywayConfig.load();
        return new FlywayMigrationInitializer(masterFlyway, null);
    }

    @Bean("slaveFlyway")
    public FlywayMigrationInitializer slaveFlyway(@Qualifier("dataSourceSlave") DataSource dataSourceSlave) {
        FluentConfiguration slaveFlywayConfig = new FluentConfiguration();
        slaveFlywayConfig.baselineOnMigrate(true)
                .dataSource(dataSourceSlave)
                // .schemas("slave")
                .locations("db/migrate/slave")
                .validateOnMigrate(true)
                .ignoreFutureMigrations(false);

        Flyway slaveFlyway = slaveFlywayConfig.load();
        return new FlywayMigrationInitializer(slaveFlyway, null);
    }
}
