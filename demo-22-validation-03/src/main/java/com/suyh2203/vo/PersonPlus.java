package com.suyh2203.vo;

import com.suyh2203.validate.PersonPlusGroupSequenceProvider;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.List;

@GroupSequenceProvider(PersonPlusGroupSequenceProvider.class)
@Data
@Validated
public class PersonPlus {

    @NotNull
    private String name;

    @NotNull
    @Range(min = 10, max = 40)
    private Integer age;

    // Default 分组时，hobbies 是允许为null 的
    @NotNull(groups = {WhenAge20And30Group.class, WhenAge30And40Group.class})
    @Size(min = 1, max = 2, groups = WhenAge20And30Group.class)
    @Size(min = 3, max = 5, groups = WhenAge30And40Group.class)
    private List<String> hobbies;

    /**
     * 定义专属的业务逻辑分组
     */
    public interface WhenAge20And30Group {
    }
    public interface WhenAge30And40Group {
    }

    /**
     * 对组的顺序进行定制，如果Default 的失败了就不会再走后面的组校验了
     */
    @GroupSequence({Default.class, WhenAge20And30Group.class})
    public interface WhenAge20And30GroupSequence {
    }

    /**
     * 对组的顺序进行定制，如果Default 的失败了就不会再走后面的组校验了
     */
    @GroupSequence({Default.class, WhenAge30And40Group.class})
    public interface WhenAge30And40GroupSequence {
    }
}
