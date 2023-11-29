package com.suyh07012.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 待召回名单，pn + pnum唯一， 外接系统提供的vo 对象
 */
@Data
public class WaitingRecallUsersVo {

    // 项目
    private String pn;

    // 手机号
    private String pnum;

    // 类型：1-试用客服，2-正式客服
    private Integer type;

    // 实物奖励数值
    private BigDecimal physical;

    // 虚拟奖励数值
    private BigDecimal virtual;

    // 优先级权重，0-100，数值越大优先级越高
    private Integer priority;

    // 下载链接
    private String link;

}
