package com.suyh.es3202.config;

import com.suyh.es3202.es.CustomEsEntityMapper;
import com.suyh.es3202.es.CustomEsResultMapper;
import com.suyh.es3202.es.CustomRepositoryImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 按配置来进行添加自定义repository
 */
@ConditionalOnProperty(name = "com.suyh.es.enable", havingValue = "true")
@EnableElasticsearchRepositories(basePackages = "com.suyh.es3202.repository", repositoryBaseClass = CustomRepositoryImpl.class)
public class EsCustomRepositoryConfiguration {

    /**
     * 注入自定义的 ResultsMapper 来代替 DefaultResultMapper
     * 本来想着是反序列化ES 的返回值，但是似乎没有成功。
     */
    @Bean
    ResultsMapper resultsMapper(SimpleElasticsearchMappingContext mappingContext, EntityMapper entityMapper) {
        return new CustomEsResultMapper(mappingContext, entityMapper);
    }

    /**
     * 注入自定义的 EntityMapper 来代替 DefaultEntityMapper
     * 本来想着是反序列化ES 的返回值，但是似乎没有成功。
     */
    @Bean
    EntityMapper entityMapper(SimpleElasticsearchMappingContext mappingContext) {
        return new CustomEsEntityMapper(mappingContext);
    }
}
