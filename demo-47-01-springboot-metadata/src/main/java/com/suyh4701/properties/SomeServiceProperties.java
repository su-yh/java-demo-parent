package com.suyh4701.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.util.unit.DataSize;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 属性封装类，将配置文件中的相关属性封装到当前类实例中
 * some.service.prefix 指定前缀的属性
 * some.service.suffix 指定后缀的属性
 */
@ConfigurationProperties("some.service")    // 这个注解需要配和@EnableConfigurationProperties 使用
@Data
public class SomeServiceProperties {
    private String prefix = "default-prefix";
    private String suffix = "default-suffix";
    /**
     * 文件大小类型
     */
    private DataSize maxSize = DataSize.parse("5MB");
    /**
     * 时间跨度
     */
    private Duration duration = Duration.parse("PT1M");
    ///**
    // * 自定义类型解析
    // */
    // private TempVo vo;
    /**
     * 该配置项为过时的配置项，这个配置项已经不再使用了。
     */
    @Deprecated
    private String deprecatedItem;
    private List<String> addresses = new ArrayList<>(Arrays.asList("localhost", "otherhost"));

    /**
     * 对于过时的配置项，使用注解添加，可以在配置文件里面被识别
     */
    @DeprecatedConfigurationProperty(reason = "原因", replacement = "可替换的key")
    public String getDeprecatedItem() {
        return "";
    }
}
