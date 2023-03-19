package com.suyh2202.validate;

import com.suyh2202.vo.Person;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

/**
 * 该接口定义了：动态Group序列的协定
 * 要想它生效，需要在{@link Person}上标注@{@link GroupSequenceProvider} 注解并且指定此类为处理类
 * 如果{@link Default}组对{@link Person}进行验证，则实际验证的实例将传递给此类以确定默认组序列（这句话特别重要  下面用例子解释）
 */
@Slf4j
public class PersonGroupSequenceProvider implements DefaultGroupSequenceProvider<Person> {

    /**
     * 合格方法是给T返回默认的组（多个）。因为默认的组是Default嘛~~~通过它可以自定指定
     * 入参T object允许在验证值状态的函数中动态组合默认组序列。（非常强大）
     * object是待校验的Bean。它可以为null哦~（Validator#validateValue的时候可以为null）
     *
     * 返回值表示默认组序列的List。它的效果同@GroupSequence定义组序列，尤其是列表List必须包含类型T

     * @param bean 实际校验的vo 对象
     * @return Default 分组列表
     */
    @Override
    public List<Class<?>> getValidationGroups(Person bean) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(Person.class); // 这一步不能省,否则Default分组都不会执行了，会抛错的

        if (bean != null) { // 这块判空请务必要做
            Integer age = bean.getAge();
            if (age == null) {
                return defaultGroupSequence;
            }
            log.info("年龄为：{}时，执行对应校验逻辑分组", age);

            if (age >= 20 && age < 30) {
                defaultGroupSequence.add(Person.WhenAge20And30Group.class);
            } else if (age >= 30 && age < 40) {
                defaultGroupSequence.add(Person.WhenAge30And40Group.class);
            }
        }
        return defaultGroupSequence;
    }
}
