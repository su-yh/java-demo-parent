package com.suyh0201.update;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Modifier;

@Component
@Slf4j
public class RefreshProperties implements SchedulingConfigurer {

    @Resource
    private Environment environment;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addCronTask(this::updateConfigValue, "* * * * * *");
    }

    public void updateConfigValue() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            log.info("beanNames: {}", beanName);
        }

        Object[] beans = new Object[beanNames.length];
        for (int i = 0; i < beanNames.length; i++) {
            beans[i] = applicationContext.getBean(beanNames[i]);
        }

        for (Object bean : beans) {
            ReflectionUtils.doWithLocalFields(bean.getClass(), field -> {
                // 忽略静态属性
                if (Modifier.isStatic(field.getModifiers())) {
                    return;
                }

                Value annotationValue = field.getAnnotation(Value.class);
                if (annotationValue == null) {
                    return;
                }

                // elText: ${spring.profiles.active}
                String elText = annotationValue.value();
                // TODO: suyh - 这里使用environment 的resolvePlaceholders() 方法它只能是更新String 类型的值
                // 不会将String 转换成对应的类型，所以要使用BeanFactory 的对应的方法。
                String curValue = environment.resolvePlaceholders(elText);
                if (curValue == null) {
                    log.warn("parseValue is null");
                    return;
                }
                Object sourceValue = field.get(bean);
                log.info("field: {}, update value: {} -> {}", field.getName(), sourceValue, curValue);
                if (!curValue.equals(sourceValue)) {
                    field.set(bean, curValue);
                }
            });
        }
    }
}
