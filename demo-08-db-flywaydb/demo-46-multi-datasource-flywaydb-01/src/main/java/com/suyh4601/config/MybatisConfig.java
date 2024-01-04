package com.suyh4601.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.suyh4601.mapper.master"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
@MapperScan(basePackages = "com.suyh4601.mapper.slave", sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class MybatisConfig {
    /**
     * 参考自：MybatisAutoConfiguration#sqlSessionFactory(javax.sql.DataSource)
     */
    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dataSourceMaster") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        String locationPattern = "classpath*:mapper/master/*.xml";
        if (StringUtils.hasText(locationPattern)) {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resource = resolver.getResources(locationPattern);
            factory.setMapperLocations(resource);
        }
        return factory.getObject();
    }

    @Bean("slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("dataSourceSlave") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        String locationPattern = "classpath*:/mapper/slave/*.xml";
        if (StringUtils.hasText(locationPattern)) {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resource = resolver.getResources(locationPattern);
            factory.setMapperLocations(resource);
        }
        return factory.getObject();
    }
}
