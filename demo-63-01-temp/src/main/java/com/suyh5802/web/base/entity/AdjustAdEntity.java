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
@TableName(value = "adjust_ad", autoResultMap = true)
@Data
public class AdjustAdEntity extends BaseMqVo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String appToken;
    private String tracker;
    @TableField("`key`")
    private String key;
    private String source;
    private String pkg;
    private String channelid;
    private String isOrganic;
    private String googleAdsCampaignId;
    private String googleAdsCampaignName;
    private String googleAdsAdgroupId;
    private String googleAdsAdgroupName;
    private String googleAdsCampaignType;
    private String googleAdsCreativeId;
    private String fbCampaignGroupName;
    private String fbCampaignGroupId;
    private String fbCampaignName;
    private String fbCampaignId;
    private String fbAdgroupName;
    private String fbAdgroupId;
    private String fbAdObjectiveName;
}
