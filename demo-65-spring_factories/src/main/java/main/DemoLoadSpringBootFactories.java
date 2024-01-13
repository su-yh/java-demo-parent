package main;

import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author suyh
 * @since 2024-01-13
 */
public class DemoLoadSpringBootFactories {
    public static void main(String[] args) {
        // springboot 读 spring.factories 的调用方法。
        List<String> envPostProcessor = SpringFactoriesLoader.loadFactoryNames(EnvironmentPostProcessor.class, DemoLoadSpringBootFactories.class.getClassLoader());
        if (envPostProcessor == null || envPostProcessor.isEmpty()) {
            System.out.println("suyh - envPostProcessor is null or empty");
        } else {
            for (String processor : envPostProcessor) {
                System.out.println("suyh - processor: " + processor);
            }
        }
    }
}
