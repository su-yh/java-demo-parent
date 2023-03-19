package com.suyh2202.vo;

import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;

@Data
public class User {

    @NotEmpty(message = "firstname may be empty")
    private String firstname;
    @NotEmpty(message = "middlename may be empty", groups = Default.class)
    private String middlename;
    @NotEmpty(message = "lastname may be empty", groups = GroupA.class)
    private String lastname;
    @NotEmpty(message = "country may be empty", groups = GroupB.class)
    private String country;


    public interface GroupA {
    }
    public interface GroupB {
    }

    /**
     * group 会按这个顺序进行校验，首先校验Default 组
     * 当Default 组校验不通过，则直接结束
     * 否则校验GroupA
     * 依次进行下去
     */
    @GroupSequence({Default.class, GroupA.class, GroupB.class})
    public interface Group {
    }
}
