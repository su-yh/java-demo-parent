package com.suyh07013.vo;

import lombok.Data;

/**
 * 成功召回名单，外接系统提供的vo 对象
 */
@Data
public class SucceedRecallUsersVo {

    // 项目
    private String pn;

    // 手机号
    private String pnum;

    // 回归日期时间戳，单位秒
    private Long ts;

    // 类型：1-下载新包，2-回归老包唤醒
    private Integer type;

}
