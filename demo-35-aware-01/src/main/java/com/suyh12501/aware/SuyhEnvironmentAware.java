package com.suyh12501.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SuyhEnvironmentAware implements EnvironmentAware {
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        String mavenHome = environment.getProperty("MAVEN_HOME");
        String windirSystemValue = environment.getProperty("windir");
        log.info("MAVEN_HOME: {}", mavenHome);
        log.info("windir: {}", windirSystemValue);
    }
}
