package com.suyh.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@SpringBootConfiguration
@PropertySource(value = "classpath:application-multidb.yml")
public class MultiDataSource {

    // 有多个相同类型的Bean 对象时，可以将其中一个指定为主要(@Primary)的，在使用@Autowired 时会首先注入此Bean 对象
    //@Primary
    @Bean(name = "readWriteDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource readWrite() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readOnlyDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DataSource readOnly() {
        return DataSourceBuilder.create().build();
    }
}
