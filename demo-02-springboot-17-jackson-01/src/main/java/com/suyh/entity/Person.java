package com.suyh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Data
public class Person {
    private String id;
    private String name;

    // 说是属性值为null 时不进行序列化
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sex;

    /**
     * 注解：@JsonIgnore 这个注解放在属性上面，则set 将不会解析，但是get 将会被序列化
     * 原来当@JsonIgnore 存在时，@JsonFormat 注解将不允许存在
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
//    @JsonIgnore
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date updateDate;

    public Person() {
    }

    public Person(String id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
