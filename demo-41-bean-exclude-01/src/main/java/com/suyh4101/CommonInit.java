package com.suyh4101;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 在springboot 启动之后立即执行下面代码
 * 与它相同功能的还有一个叫 CommandLineRunner的接口
 * 唯一不同的就是args 的参数类型。
 * <p>
 * 需要实现 ApplicationRunner 接口
 */
@Component
// 要获取一git 的一些信息，需要在pom.xml 中添加一些插件
@PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = true)
@Order(100)
@Slf4j
public class CommonInit implements ApplicationRunner {

    // 上下文
    private static ApplicationContext CONTEXT = null;

    /**
     * 获取 bean 对象
     */
    public static Object getBean(String name) throws BeansException {
        return CONTEXT.getBean(name);
    }

    /**
     * 获取 bean 对象
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return (T) CONTEXT.getBean(clazz);
    }

    /**
     * 直接通过spring bean 来获取到环境上下文数据。
     * 这个对象就是 SpringApplication.run() 调用返回的那个对象。
     * 将地址打印出来可以看出地址是完全相同的。
     */
    @Resource
    private ConfigurableApplicationContext ctx;

    @Value("${git.tags:UNKNOWN-GIT-TAGS}")
    private String gitTags;

    @Value("${git.branch:UNKNOWN-BRANCH}")
    private String gitBranch;

    @Value("${git.commit.id:UNKNOWN-COMMIT-ID}")
    private String gitCommitId;

    @Value("${git.build.version:UNKNOWN-BUILD-VERSION}")
    private String gitBuildVersion;

    @Value("${git.build.time:UNKNOWN-BUILD-TIME}")
    private String gitBuildTime;

    @Value("${git.commit.time:UNKNOWN-COMMIT-TIME}")
    private String gitCommitTime;

    /**
     * 在这里人寿初始化工作
     *
     * @param args 这个应该是命令行参数
     */
    @Override
    public void run(ApplicationArguments args) {
        CONTEXT = ctx;

        String[] sourceArgs = args.getSourceArgs();
        log.info("application source args length: {}", sourceArgs.length);
        for (String arg : sourceArgs) {
            log.info("arg: {}", arg);
        }
        log.info("application source args: {}", ToStringBuilder.reflectionToString(
                args, ToStringStyle.JSON_STYLE));

        log.info("ApplicationRunnerInit ctx: " + ctx);
        log.info("git information, tags: {}, branch: {}, id: {}, build version: {}, build time: {}, commit time: {}",
                gitTags, gitBranch, gitCommitId, gitBuildVersion, gitBuildTime, gitCommitTime);
    }
}
