package com.suyh.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class ChildEntity implements Serializable {
    private static final long serialVersionUID = 33442L;

    @Min(1000)
    @Max(9999)
    private Integer id;

    @NotBlank   // 不允许为空白字符串，需要在trim() 之后非空
    @Size(max = 10, message = "name 最大长度为{max}")
    private String name;

    @NotEmpty   // 不允许为空，允许空白字符串
    private String desc;

    @Range(min = 1000, max = 9999, message = "非法值，有效范围：[{min}, {max}]")
    private Integer num;
}
