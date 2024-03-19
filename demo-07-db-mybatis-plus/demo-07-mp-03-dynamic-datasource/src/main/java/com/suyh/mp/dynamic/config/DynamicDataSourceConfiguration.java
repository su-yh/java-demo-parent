package com.suyh.mp.dynamic.config;

import com.suyh.mp.dynamic.config.properties.DynamicDataSourceProviderProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author suyh
 * @since 2024-03-17
 */
@EnableConfigurationProperties(DynamicDataSourceProviderProperties.class)
@Configuration
public class DynamicDataSourceConfiguration {

    // 不能创建DataSource 相关的bean 对象，因为创建出来之后 DynamicRoutingDataSource  这个bean 就创建不出来了。
    // 最终会导致一些sqlsession 的报错，至于具体是什么原因，暂时还不知晓。
//    @Bean("dataSource001")
//    @ConfigurationProperties(prefix = "suyh.datasource.hikari.ds001")
//    public HikariDataSource masterDruidDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean("dataSource002")
//    @ConfigurationProperties(prefix = "suyh.datasource.hikari.ds002")
//    public HikariDataSource slaveDruidDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean
//    public DataSource dataSource(DynamicDataSourceProperties properties) {
//        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
//        dataSource.setPrimary(properties.getPrimary());
//        dataSource.setStrict(properties.getStrict());
//        dataSource.setStrategy(properties.getStrategy());
//        dataSource.setP6spy(properties.getP6spy());
//        dataSource.setSeata(properties.getSeata());
//        return dataSource;
//    }
}
