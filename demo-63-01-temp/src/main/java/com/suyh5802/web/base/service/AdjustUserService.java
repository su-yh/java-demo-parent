package com.suyh5802.web.base.service;

import com.suyh5802.web.base.entity.AdjustAdEntity;
import com.suyh5802.web.base.entity.AdjustUserEntity;
import com.suyh5802.web.base.mapper.AdjustUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @author suyh
 * @since 2023-12-27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdjustUserService {
    private final AdjustAdService adjustAdService;
    private final AdjustUserMapper adjustUserMapper;

    @PostConstruct
    public void init() {
        // 是否启用下面的代码功能
        boolean enabled = false;
        if (!enabled) {
            return;
        }

        // 查询id大于432937L 的其他数据，这个数据不是随机生成的。
        List<AdjustAdEntity> adEntities = adjustAdService.queryAdjustAdEntities();

        long currentTimeMillis = System.currentTimeMillis();
        long timestampSecond = currentTimeMillis / 1000;

        LocalDate localDate = LocalDate.now();
        String dateString = localDate.toString().replace("-", "");
        int dateInt = Integer.parseInt(dateString);

        for (AdjustAdEntity adEntity : adEntities) {
            StopWatch stopWatch = new StopWatch("AdjustUser_initData");
            stopWatch.start("init-data, ad id: " + adEntity.getId());
            for (int i = 0; i < 1000; i++) {
                String uuid = UUID.randomUUID().toString().replace("-", "");
                AdjustUserEntity entity = new AdjustUserEntity();
                entity.setGaid(uuid).setPkg(adEntity.getPkg()).setChannelid(adEntity.getChannelid()).setAppid(uuid)
                        .setAppNameDashboard(uuid).setAppToken(adEntity.getAppToken()).setTracker(adEntity.getTracker())
                        .setCampaignName(uuid).setAdgroupName(uuid).setCreativeName(uuid)
                        .setClickReferer(uuid).setInstalledAt("" + timestampSecond).setDeviceName("Y53s")
                        .setOsVersion("13").setTimezone("UTC+0530").setEventName("")
                        .setDeeplink("").setCostAmount("")
                        .setGoogleAdsCampaignType("ACI")
                        .setGoogleAdsCampaignName(adEntity.getGoogleAdsCampaignName()).setGoogleAdsCampaignId(adEntity.getGoogleAdsCampaignId())
                        .setGoogleAdsAdgroupId(adEntity.getGoogleAdsAdgroupId()).setGoogleAdsAdgroupName(adEntity.getGoogleAdsAdgroupName())
                        .setGoogleAdsCreativeId(adEntity.getGoogleAdsCreativeId()).setGoogleAdsKeyword(uuid).setGoogleAdsPlacement(uuid)
                        .setFbCampaignGroupId(adEntity.getFbCampaignGroupId()).setFbCampaignGroupName(adEntity.getFbCampaignGroupName())
                        .setFbCampaignId(adEntity.getFbCampaignId()).setFbCampaignName(adEntity.getFbCampaignName())
                        .setFbAdgroupName(adEntity.getFbAdgroupName()).setFbAdgroupId(adEntity.getFbAdgroupId())
                        .setFbAdObjectiveName(adEntity.getFbAdObjectiveName())
                        .setAdid(adEntity.getId() + "").setIsOrganic(adEntity.getIsOrganic()).setIpAddress(uuid)
                        .setCity("").setIsp("").setLanguage("en")
                        .setKey(adEntity.getKey()).setCts(timestampSecond).setDates(dateInt).setWebUuid(uuid);

                adjustUserMapper.insert(entity);
            }
            stopWatch.stop();
            log.info("stop watch result: {}", stopWatch.prettyPrint());
        }

        log.info("AdjustUserService init finished.");
    }
}
