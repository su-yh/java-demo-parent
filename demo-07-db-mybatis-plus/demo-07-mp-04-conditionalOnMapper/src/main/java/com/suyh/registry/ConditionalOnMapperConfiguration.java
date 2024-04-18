package com.suyh.registry;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
import org.springframework.lang.NonNull;

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
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException {
        registryBeanConditionalOnMapper(registry, TestUuidService.class, TestUuidMapper.class);
        registryBeanConditionalOnMapper(registry, TestLongIdService.class, TestLongIdMapper.class);
    }

    // 这样的话，就需要mapper 参数是每一个，其他的按顺序给出
    // 另一个要求就是，beanName 必须是类名首字母小写其他完全一致。
    // 否则这里就没法构造了。
    private <ENTITY, MAPPER extends BaseMapper<ENTITY>, BEAN> void registryBeanConditionalOnMapper(
            BeanDefinitionRegistry registry, Class<BEAN> beanClass, Class<MAPPER> mapperClass, Class<?>... constructorArgs) {
        // 判断ChannelMapper 对应的BeanDefinition 是否存在，若不存在则跳过，否则将ChannelService 注册成一个BeanDefinition
        String mapperBeanName = Introspector.decapitalize(mapperClass.getSimpleName());
        if (!registry.containsBeanDefinition(mapperBeanName)) {
            log.warn("cannot found BeanDefinition by mapper({}).", mapperClass.getSimpleName());
            return;
        }

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        builder.addConstructorArgReference(mapperBeanName);
        if (constructorArgs != null && constructorArgs.length > 0) {
            for (Class<?> constructorArg : constructorArgs) {
                String argBeanName = Introspector.decapitalize(constructorArg.getSimpleName());
                builder.addConstructorArgReference(argBeanName);
            }
        }

        if (TestUuidService.class.isAssignableFrom(beanClass)) {
            builder.addPropertyValue("enabled", false);
        }

        String candidateBeanName = Introspector.decapitalize(beanClass.getSimpleName());
        registry.registerBeanDefinition(candidateBeanName, builder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // do nothing
    }
}
