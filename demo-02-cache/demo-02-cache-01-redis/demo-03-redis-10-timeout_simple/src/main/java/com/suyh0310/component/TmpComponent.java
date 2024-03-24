package com.suyh0310.component;

import com.suyh0310.vo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author suyh
 * @since 2023-11-09
 */
@Component
@Slf4j
public class TmpComponent {
    private static int number = 0;

    //
    // 源码所需要重点关注的类：CacheInterceptor  CacheAspectSupport
    /**
     * {@link @CacheEvict} 是用来标注在需要清除缓存元素的方法或类上的。
     * 当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
     * CacheEvict可以指定的属性有value(cacheNames)、key、condition、allEntries和beforeInvocation。
     * 其中value(cacheNames)、key和condition的语义与@Cacheable对应的属性类似。
     * 即value(cacheNames)表示清除操作是发生在哪些Cache上的（对应Cache的名称）；key表示需要清除的是哪个key，如未指定则会使用默认策略生成的key；
     * condition表示清除操作发生的条件。下面我们来介绍一下新出现的两个属性allEntries和beforeInvocation。
     */
    // unless = "#result == null" ，当方法返回值为null 时将不会缓存，只有当返回值非null 时，才会缓存数据。
    // 所以这里生成的redis 的缓存名称规则就是 缓存的前缀 + cacheNames + :: + spEL解析出来key 的值
    @Cacheable(cacheNames = "suyh-default", key = "#key01", unless = "#result == null")
    public String getDefault(String key01) {
        log.info("getDefault 被调用");

        if (number++ == 0) {
            return null;
        }

        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(nowTime);
    }

    @CacheEvict(cacheNames = "suyh-default", key = "#key01")
    public String updateDefault(String key01) {
        log.info("updateDefault 被调用");

       return "updateDefault";
    }

    @Cacheable(cacheNames = "suyh-default", unless = "#result == null")
    public Student getStudent() {
        log.info("getStudent 被调用");

        Student student = new Student();
        student.setId(number + "").setName("suy");
        number++;
        return student;
    }

}