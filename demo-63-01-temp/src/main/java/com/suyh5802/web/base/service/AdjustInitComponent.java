package com.suyh5802.web.base.service;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.suyh5802.web.base.entity.AdjustAdEntity;
import com.suyh5802.web.base.entity.AdjustUserEntity;
import com.suyh5802.web.base.mapper.AdjustAdMapper;
import com.suyh5802.web.base.mapper.AdjustUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

/**
 * @author suyh
 * @since 2023-12-29
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AdjustInitComponent {
    private final AdjustAdMapper adjustAdMapper;
    private final AdjustUserMapper adjustUserMapper;

    @PostConstruct
    public void init() {
        // 是否启用下面的代码功能
        boolean enabled = false;
        if (!enabled) {
            return;
        }

        long currentTimeMillis = System.currentTimeMillis();
        long timestampSecond = currentTimeMillis / 1000;
        LocalDate localDate = LocalDate.now();
        String dateString = localDate.toString().replace("-", "");
        int dateInt = Integer.parseInt(dateString);

        String[] pkgs = {"com.sn.oos", "com.rruo.goldennludo.ogott"};
        String[] sources = {"FB", "GG"};
        String[] origanices = {"0", "1"};
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            int randValue = random.nextInt(9000000) + 1000000;
            String channelid = "slg_" + randValue;

            int indexSrc = randValue % sources.length;
            String source = sources[indexSrc];
            int indexOrg = randValue % origanices.length;
            String origanic = origanices[indexOrg];
            long googleAdsCampaignId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long googleAdsAdgroupId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long googleAdsCreativeId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long fbCampaignGroupId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long fbCampaignId = DefaultIdentifierGenerator.getInstance().nextId(null);
            long fbAdgroupId = DefaultIdentifierGenerator.getInstance().nextId(null);

            {
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

            int pkgIndex = randValue % 2;
            String pkg = pkgs[pkgIndex];

            {
                AdjustUserEntity entity = new AdjustUserEntity();
                entity.setGaid(uuid).setPkg(pkg).setChannelid(channelid).setAppid(pkg)
                        .setAppNameDashboard(pkg).setAppToken("111111").setTracker("tracker")
                        .setCampaignName(uuid).setAdgroupName(uuid).setCreativeName(uuid)
                        .setClickReferer(uuid).setInstalledAt("" + timestampSecond).setDeviceName("Y53s")
                        .setOsVersion("13").setTimezone("UTC+0530").setEventName("")
                        .setDeeplink("").setCostAmount("")
                        .setGoogleAdsCampaignType("ACI").setGoogleAdsCampaignName(googleAdsCampaignId + "").setGoogleAdsCampaignId(googleAdsCampaignId + "")
                        .setGoogleAdsAdgroupId(googleAdsAdgroupId + "").setGoogleAdsAdgroupName(googleAdsAdgroupId + "")
                        .setGoogleAdsCreativeId(googleAdsCreativeId + "").setGoogleAdsKeyword(uuid).setGoogleAdsPlacement(uuid)
                        .setFbCampaignGroupId(uuid).setFbCampaignGroupName(uuid)
                        .setFbCampaignId(fbCampaignId + "").setFbCampaignName(fbCampaignId + "")
                        .setFbAdgroupName(fbAdgroupId + "").setFbAdgroupId(fbAdgroupId + "").setFbAdObjectiveName(uuid)
                        .setAdid(uuid).setIsOrganic(origanic).setIpAddress(uuid)
                        .setCity("").setIsp("").setLanguage("en")
                        .setKey(uuid).setCts(timestampSecond).setDates(dateInt).setWebUuid(uuid);

                adjustUserMapper.insert(entity);
            }
        }

    }
}
