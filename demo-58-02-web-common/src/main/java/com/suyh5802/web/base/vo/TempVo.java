package com.suyh5802.web.base.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author suyh
 * @since 2023-11-26
 */
@Data
public class TempVo {
    @NotNull
    private Long id;
    private String username;
    private Integer age;
}
