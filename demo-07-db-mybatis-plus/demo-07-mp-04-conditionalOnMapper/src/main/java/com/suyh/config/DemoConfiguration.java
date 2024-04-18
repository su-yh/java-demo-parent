package com.suyh.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.suyh.registry.ConditionalOnMapperRegistrar;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScannerRegistrar;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 问题：从目前来看，MapperScannerRegistrar 的优先级总是在{@link ConditionalOnMapperRegistrar} 之前，这是什么原因？
 * 根据 ConfigurationClassParser.collectImports(...)  方法的实现逻辑，优先添加非@Import 解决的导入类
 * 所以MapperScannerRegistrar 的优先级总是比{@link ConditionalOnMapperRegistrar} 的高。
 *
 * 不过，这里的核心也就是需要{@link ConditionalOnMapperRegistrar} 的优先级比{@link MapperScannerRegistrar} 低。
 *
 * @author suyh
 * @since 2024-04-17
 */
@Configuration
@MapperScan("com.suyh.mapper")
@Import(ConditionalOnMapperRegistrar.class)
@AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
public class DemoConfiguration {

// TODO: suyh - 目前来看，以下的方式，没法满足按条件注入需求
//    @DependsOn("channelMapper")
//    @ConditionalOnBean(ChannelMapper.class)
//    @Bean
//    public ChannelService channelService(ChannelMapper channelMapper) {
//        return new ChannelService(channelMapper);
//    }
}
