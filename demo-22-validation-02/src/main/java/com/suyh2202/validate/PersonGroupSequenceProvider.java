package com.suyh2202.validate;

import com.suyh2202.vo.Person;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PersonGroupSequenceProvider implements DefaultGroupSequenceProvider<Person> {

    @Override
    public List<Class<?>> getValidationGroups(Person bean) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(Person.class); // 这一步不能省,否则Default分组都不会执行了，会抛错的

        if (bean != null) { // 这块判空请务必要做
            Integer age = bean.getAge();
//            if (age == null) {
//
//            }
            System.err.println("年龄为：" + age + "，执行对应校验逻辑");

            if (age >= 20 && age < 30) {
                defaultGroupSequence.add(Person.WhenAge20And30Group.class);
            } else if (age >= 30 && age < 40) {
                defaultGroupSequence.add(Person.WhenAge30And40Group.class);
            }
        }
        return defaultGroupSequence;
    }
}
