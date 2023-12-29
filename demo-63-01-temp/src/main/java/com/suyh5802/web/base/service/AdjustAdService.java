package com.suyh5802.web.base.service;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.suyh5802.web.base.entity.AdjustAdEntity;
import com.suyh5802.web.base.mapper.AdjustAdMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.UUID;

/**
 * @author suyh
 * @since 2023-12-27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdjustAdService {
    private final AdjustAdMapper adjustAdMapper;

    @PostConstruct
    public void init() {
        // 是否启用下面的代码功能
        boolean enabled = false;
        if (!enabled) {
            return;
        }

        String[] sources = {"FB", "GG"};
        String[] origanices = {"0", "1"};
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            int channelNumber = random.nextInt(9000000) + 1000000;
            String channelid = "slg_" + channelNumber;

            int indexSrc = channelNumber % sources.length;
            String source = sources[indexSrc];
            int indexOrg = channelNumber % origanices.length;
            String origanic = origanices[indexOrg];
            long googleAdsCampaignId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long googleAdsAdgroupId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long googleAdsCreativeId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long fbCampaignGroupId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long fbCampaignId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long fbAdgroupId = DefaultIdentifierGenerator.getInstance().nextId(null);

            AdjustAdEntity entity = new AdjustAdEntity();
            entity.setAppToken("apptoken").setTracker("tracker").setKey(uuid)
                    .setSource(source).setPkg("com.jQWQIQU.GWGTGB").setChannelid(channelid).setIsOrganic(origanic)
                    .setGoogleAdsCampaignId(googleAdsCampaignId +"").setGoogleAdsCampaignName(googleAdsCampaignId + "")
                    .setGoogleAdsAdgroupId(googleAdsAdgroupId + "").setGoogleAdsAdgroupName(googleAdsAdgroupId + "")
                    .setGoogleAdsCampaignType("").setGoogleAdsCreativeId(googleAdsCreativeId + "")
                    .setFbCampaignGroupName(fbCampaignGroupId + "").setFbCampaignGroupId(fbCampaignGroupId + "")
                    .setFbCampaignName(fbCampaignId + "").setFbCampaignId(fbCampaignId + "")
                    .setFbAdgroupName(fbAdgroupId + "").setFbAdgroupId(fbAdgroupId + "")
                    .setFbAdObjectiveName("name");
            adjustAdMapper.insert(entity);
        }

    }
}
