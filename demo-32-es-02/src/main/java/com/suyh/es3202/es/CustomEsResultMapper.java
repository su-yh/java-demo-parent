package com.suyh.es3202.es;

import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentProperty;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.lang.Nullable;

public class CustomEsResultMapper extends DefaultResultMapper {

    public CustomEsResultMapper(
            MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext,
            @Nullable EntityMapper entityMapper) {
        super(mappingContext, entityMapper);
    }
}
