package com.suyh.es3202.config;

import com.suyh.es3202.es.CustomRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 按配置来进行添加自定义repository
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.suyh.es3202.repository", repositoryBaseClass = CustomRepositoryImpl.class)
public class EsCustomRepositoryConfiguration {
}
