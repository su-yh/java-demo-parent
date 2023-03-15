package com.suyh5601.argument.bind;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CurrUserVo {
    private Long id;

    @NotBlank
    private String name;
}
