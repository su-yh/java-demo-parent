package com.suyh5802.web.base.service;

import com.suyh5802.web.base.entity.AdjustUserEntity;
import com.suyh5802.web.base.mapper.AdjustUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final AdjustUserMapper adjustUserMapper;

    // @PostConstruct
    public void init() {
        List<AdjustUserEntity> entities = adjustUserMapper.selectList(null);
        System.out.println("entities: " + entities);

        adjustUserMapper.delete(null);

        String uuid = UUID.randomUUID().toString().replace("-", "");
        AdjustUserEntity entity = new AdjustUserEntity();
        entity.setGaid(uuid).setPkg(uuid).setChannelid(uuid).setAppid(uuid)
                        .setAppNameDashboard(uuid).setAppToken(uuid).setTracker(uuid)
                        .setCampaignName(uuid).setAdgroupName(uuid).setCreativeName(uuid)
                        .setClickReferer(uuid).setInstalledAt(uuid).setDeviceName(uuid)
                        .setOsVersion(uuid).setTimezone(uuid).setEventName(uuid)
                        .setDeeplink(uuid).setCostAmount(uuid)
                        .setGoogleAdsCampaignType(uuid).setGoogleAdsCampaignName(uuid).setGoogleAdsCampaignId(uuid)
                        .setGoogleAdsAdgroupId(uuid).setGoogleAdsAdgroupName(uuid)
                        .setGoogleAdsCreativeId(uuid).setGoogleAdsKeyword(uuid).setGoogleAdsPlacement(uuid)
                        .setFbCampaignGroupId(uuid).setFbCampaignGroupName(uuid)
                        .setFbCampaignId(uuid).setFbCampaignName(uuid)
                        .setFbAdgroupName(uuid).setFbAdgroupId(uuid).setFbAdObjectiveName(uuid)
                        .setAdid(uuid).setIsOrganic("1").setIpAddress(uuid)
                        .setCity(uuid).setIsp(uuid).setLanguage(uuid)
                        .setKey(uuid).setCts(1L).setDates(2).setWebUuid(uuid);



        adjustUserMapper.insert(entity);
    }
}
