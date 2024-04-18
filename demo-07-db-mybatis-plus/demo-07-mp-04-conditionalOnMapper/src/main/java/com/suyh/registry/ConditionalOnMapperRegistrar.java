package com.suyh.registry;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

import java.beans.Introspector;

/**
 * 基于 mapper bean 是否存在而注入某个bean
 *
 * @author suyh
 * @since 2024-04-18
 */
public class ConditionalOnMapperRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(
            @NonNull AnnotationMetadata importingClassMetadata,
            BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ConditionalOnMapperConfiguration.class);

        String beanName = Introspector.decapitalize(ConditionalOnMapperConfiguration.class.getSimpleName());
        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }
}
