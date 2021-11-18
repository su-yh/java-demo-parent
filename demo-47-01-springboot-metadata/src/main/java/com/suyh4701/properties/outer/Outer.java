package com.suyh4701.properties.outer;

import com.suyh4701.properties.inner.ActiveEnvironment;
import com.suyh4701.properties.inner.ContainerType;
import com.suyh4701.properties.inner.Inner;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("some.outer")
@Data
public class Outer {
    private String host;
    private Integer port;

    /**
     * 对于对象类型，且对象类型不是内部类的情况，可以使用此注解来关联。
     */
    @NestedConfigurationProperty
    private Inner inner;

    @NestedConfigurationProperty
    private ActiveEnvironment activeEnv = ActiveEnvironment.NONE;

    @NestedConfigurationProperty
    private ContainerType containerType = ContainerType.SIMPLE;
}
