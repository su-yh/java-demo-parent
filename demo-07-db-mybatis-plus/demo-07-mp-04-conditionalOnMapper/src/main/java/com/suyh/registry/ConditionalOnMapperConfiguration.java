package com.suyh.registry;

import com.suyh.mapper.TestLongIdMapper;
import com.suyh.mapper.TestUuidMapper;
import com.suyh.service.TestLongIdService;
import com.suyh.service.TestUuidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.beans.Introspector;

/**
 * 专门用来处理当某个 mapper 存在时才注册相对应的候选bean 对象
 *
 * @author suyh
 * @since 2024-04-18
 */
@Slf4j
public class ConditionalOnMapperConfiguration implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        registryBeanConditionalOnMapper(registry, TestUuidMapper.class, TestUuidService.class);
        registryBeanConditionalOnMapper(registry, TestLongIdMapper.class, TestLongIdService.class);
    }

    private <MAPPER, BEAN> void registryBeanConditionalOnMapper(
            BeanDefinitionRegistry registry, Class<MAPPER> mapperClass, Class<BEAN> beanClass) {
        // 判断ChannelMapper 对应的BeanDefinition 是否存在，若不存在则跳过，否则将ChannelService 注册成一个BeanDefinition
        String mapperBeanName = Introspector.decapitalize(mapperClass.getSimpleName());
        if (!registry.containsBeanDefinition(mapperBeanName)) {
            System.out.println("channelMapper is not exists.");
            log.warn("cannot found BeanDefinition by mapper({}).", mapperClass.getSimpleName());
            return;
        }

        System.out.println("channelMapper is exists.");

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        builder.addConstructorArgReference(mapperBeanName);

        String candidateBeanName = Introspector.decapitalize(beanClass.getSimpleName());
        registry.registerBeanDefinition(candidateBeanName, builder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // donothing
    }
}
