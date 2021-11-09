//package com.suyh4601.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.util.StringUtils;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class MasterMybatisConfig {
////    /**
////     * 注意，此处需要使用MybatisSqlSessionFactoryBean，不是SqlSessionFactoryBean，
////     * 否则，使用mybatis-plus的内置函数时就会报invalid bound statement (not found)异常
////     * @param dataSource
////     * @return
////     * @throws Exception
////     */
////    @Bean("masterSqlSessionFactory")
////    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dataSourceMaster") DataSource dataSource) throws Exception {
////        // 设置数据源
////        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
////        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
////        //mapper的xml文件位置
////        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
////        String locationPattern = "classpath*:/mapper/master/*.xml";
////        mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources(locationPattern));
////        //对应数据库的entity位置
////        String typeAliasesPackage = "com.suyh4601.entity.master";
////        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
////        return mybatisSqlSessionFactoryBean.getObject();
////    }
//
//    private final ResourceLoader resourceLoader;
//
//    //    public MasterMybatisConfig(MybatisProperties properties, ResourceLoader resourceLoader) {
////        this.properties = properties;
////        this.resourceLoader = resourceLoader;
////    }
//    public MasterMybatisConfig(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//
//    /**
//     * 参考自：org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#sqlSessionFactory(javax.sql.DataSource)
//     *
//     * @param dataSource
//     * @return
//     * @throws Exception
//     */
//    @Bean("masterSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceMaster") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setVfs(SpringBootVFS.class);
//        String locationPattern = "classpath*:mapper/master/*.xml";
//        if (StringUtils.hasText(locationPattern)) {
//            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//            Resource[] resource = resolver.getResources(locationPattern);
//            factory.setMapperLocations(resource);
//        }
//        applyConfiguration(factory);
////        if (this.properties.getConfigurationProperties() != null) {
////            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
////        }
//        // 拦截器
////        if (!ObjectUtils.isEmpty(this.interceptors)) {
////            factory.setPlugins(this.interceptors);
////        }
////        if (this.databaseIdProvider != null) {
////            factory.setDatabaseIdProvider(this.databaseIdProvider);
////        }
////        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
////            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
////        }
////        if (this.properties.getTypeAliasesSuperType() != null) {
////            factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
////        }
////        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
////            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
////        }
////        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
////            factory.setMapperLocations(this.properties.resolveMapperLocations());
////        }
//
//        return factory.getObject();
//    }
//
//    private void applyConfiguration(SqlSessionFactoryBean factory) {
////        org.apache.ibatis.session.Configuration configuration = this.properties.getConfiguration();
////        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
////            configuration = new org.apache.ibatis.session.Configuration();
////        }
////        if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
////            for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
////                customizer.customize(configuration);
////            }
////        }
////        factory.setConfiguration(configuration);
//    }
//}
