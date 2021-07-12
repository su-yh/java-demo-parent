package com.suyh0201.environment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置中心的加载类
 */
@Component
@Slf4j
public class ConfigCenterLoader implements SchedulingConfigurer, BeanFactoryAware {

    /**
     * 配置中心属性名，用于存放在系统属性中。
     */
    public static final String CONFIG_CENTER_NAME = "configCenter";

    @Resource
    private ConfigurableEnvironment environment;

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 批量更新定时任务，首次执行延迟60 秒，之后每10 秒执行一次
        taskRegistrar.addFixedDelayTask(
                new IntervalTask(this::refreshConfigProperties, 10000, 60000)
        );
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    /**
     * 使用配置中心的值来刷新对应的spring 配置属性
     *
     */
    public void refreshEnvironmentPropertiesFromConfigCenter() {
        // 配置值
        Map<String, Object> configCenterProperties = new HashMap<>();
        MapPropertySource mapPropertySource = new MapPropertySource(CONFIG_CENTER_NAME, configCenterProperties);
        MutablePropertySources sources = environment.getPropertySources();
        if (sources.contains(CONFIG_CENTER_NAME)) {
            sources.replace(CONFIG_CENTER_NAME, mapPropertySource);
        } else {
            sources.addLast(mapPropertySource);
        }
    }

    private void refreshConfigProperties() {
        refreshEnvironmentPropertiesFromConfigCenter();
        updateValueProperties();
        updateConfigurationProperties();
    }

    /**
     * 所有的bean 对象的属性，只要使用了@Value 注解，则检查配置中心的值是否有变化，有变化则更新，否则保持不变。
     */
    private void updateValueProperties() {
        log.info("updateValueConfig");

        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object bean = beanFactory.getBean(beanName);
            refreshPropertiesInject(bean, beanName);
        }
    }

    /**
     * 对指定的bean对象进行属性注入对象的属性更新
     *
     * @param bean bean instance
     */
    private void refreshPropertiesInject(Object bean, String beanName) {
        Class<?> targetClass = bean.getClass();

        // 遍历当前类与继承的父类
        do {
            // 遍历每一个filed
            ReflectionUtils.doWithLocalFields(targetClass, field -> {
                // 忽略静态属性
                if (Modifier.isStatic(field.getModifiers())) {
                    return;
                }

                Value annotationValue = field.getAnnotation(Value.class);
                if (annotationValue == null) {
                    return;
                }

                DependencyDescriptor desc = new DependencyDescriptor(field, true);
                desc.setContainingClass(bean.getClass());

                // 这里是借鉴了 AutowiredAnnotationBeanPostProcessor.AutowiredFieldElement.resolveFieldValue(..) 方法
                // 解析对应bean 对象里面的该字段的依赖值(包括@Value、@Autowired、@Resource等属性依赖注入)
                // 但是这里前面已经过滤了，所以这里实际只会处理@Value
                Object curValue = beanFactory.resolveDependency(desc, beanName);
                if (curValue == null) {
                    log.warn("parseValue is null");
                    return;
                }

                field.setAccessible(true);
                Object sourceValue = field.get(bean);
                log.trace("field: {}, source: {}, newest: {}", field.getName(), sourceValue, curValue);
                if (!curValue.equals(sourceValue)) {
                    log.debug("field: {}, update value: {} -> {}", field.getName(), sourceValue, curValue);
                    field.set(bean, curValue);
                }
            });
            targetClass = targetClass.getSuperclass();
        } while (targetClass != null && targetClass != Object.class);
    }

    private void updateConfigurationProperties() {
        // TODO: suyh - 使用最新的配置值更新@ConfigurationProperties 对应的配置属性值。
        log.info("updateConfigurationProperties");
    }

}
