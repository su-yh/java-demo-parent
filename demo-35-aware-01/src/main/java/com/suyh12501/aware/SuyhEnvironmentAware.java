package com.suyh12501.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SuyhEnvironmentAware implements EnvironmentAware, InitializingBean {
    private Environment environment;

    // 可以通过这样的该获取环境变量的值
    @Value("${MAVEN_HOME:no_maven_home")
    private String mavenHome;

    @Value("${windir:nowindir")
    private String windir;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        String mavenHome = environment.getProperty("MAVEN_HOME");
        String windirSystemValue = environment.getProperty("windir");
        log.info("MAVEN_HOME: {}", mavenHome);
        log.info("windir: {}", windirSystemValue);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("init, MAVEN_HOME: {}", mavenHome);
        log.info("init, windir: {}", windir);
    }
}
