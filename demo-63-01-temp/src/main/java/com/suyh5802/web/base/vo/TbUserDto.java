package com.suyh5802.web.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册事件
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbUserDto {

    private String correlationId;

    private String uid;

    private String gaid;

    private String channel;

    private long ctime;

    private String pn; //产品

    private String adCampaignKey;

    private long regDate; //用户真正的注册时间

    private long statRegDate; //统计用的用户注册时间，因为某些情况下，无法从user表中获取到用户的注册日期

    private String hashTime;

    private String day;

    private long dynamicDimension;
}
