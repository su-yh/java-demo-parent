package com.suyh5802.web.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author suyh
 * @since 2023-12-27
 */
@TableName(value = "adjust_user", autoResultMap = true)
@Data
public class AdjustUserEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String gaid;
    private String pkg;
    private String channelid;
    private String appid;
    private String appNameDashboard;
    private String appToken;
    private String tracker;
    private String campaignName;
    private String adgroupName;
    private String creativeName;
    private String clickReferer;
    private String installedAt;
    private String deviceName;
    private String osVersion;
    private String timezone;
    private String eventName;
    private String deeplink;
    private String costAmount;
    private String googleAdsCampaignType;
    private String googleAdsCampaignName;
    private String googleAdsCampaignId;
    private String googleAdsAdgroupId;
    private String googleAdsAdgroupName;
    private String googleAdsCreativeId;
    private String googleAdsKeyword;
    private String googleAdsPlacement;
    private String fbCampaignGroupId;
    private String fbCampaignGroupName;
    private String fbCampaignId;
    private String fbCampaignName;
    private String fbAdgroupName;
    private String fbAdgroupId;
    private String fbAdObjectiveName;
    private String adid;
    private String isOrganic;
    private String ipAddress;
    private String city;
    private String isp;
    private String language;
    @TableField("`key`")
    private String key;

    private Long cts;
    private Integer dates;
    private String webUuid;
}
