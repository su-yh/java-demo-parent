package com.suyh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Data
@NoArgsConstructor
public class Person  implements Serializable {
    private static final long serialVersionUID = 8843L;

    private String id;
    private String name;
    private String sex;

    /**
     * 注解：@JsonIgnore 这个注解放在属性上面，则set 将不会解析，但是get 将会被序列化
     * 原来当@JsonIgnore 存在时，@JsonFormat 注解将不允许存在
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;

    // 使用默认格式化
    private Date updateDate;

    public Person(String id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
