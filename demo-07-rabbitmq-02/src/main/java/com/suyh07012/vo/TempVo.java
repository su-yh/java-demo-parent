package com.suyh07012.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author suyh
 * @since 2023-11-29
 */
@Data
public class TempVo {
    private Long messageId;
    private String messageData;
    private Date createTime;
}
