package com.suyh.es3202.es;

import org.springframework.data.elasticsearch.core.DefaultEntityMapper;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentProperty;
import org.springframework.data.mapping.context.MappingContext;

public class CustomEsEntityMapper extends DefaultEntityMapper {
    public CustomEsEntityMapper(MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> context) {
        super(context);
    }
}
