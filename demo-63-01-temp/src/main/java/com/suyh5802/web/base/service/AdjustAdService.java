package com.suyh5802.web.base.service;

import com.suyh5802.web.base.mapper.AdjustAdMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author suyh
 * @since 2023-12-27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdjustAdService {
    private final AdjustAdMapper adjustAdMapper;

    // @PostConstruct
    public void init() {
//        List<AdjustAdEntity> entities = adjustAdMapper.selectList(null);
//        System.out.println("entities: " + entities);
//
//        adjustAdMapper.delete(null);
//
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//
//        AdjustAdEntity entity = new AdjustAdEntity();
//        entity.setAppToken("apptoken").setTracker("tracker").setKey(uuid)
//                        .setSource("source").setPkg("pkg").setChannelid("chid")
//                        .setIsOrganic("1").setGoogleAdsCampaignId("id").setGoogleAdsCampaignName("name")
//                        .setGoogleAdsAdgroupId("id").setGoogleAdsAdgroupName("name")
//                        .setGoogleAdsCampaignType("type").setGoogleAdsCreativeId("id")
//                        .setFbCampaignGroupName("groupname").setFbCampaignGroupId("groupid")
//                        .setFbCampaignName("caname").setFbCampaignId("id")
//                        .setFbAdgroupName("adgroupname").setFbAdgroupId("id")
//                        .setFbAdObjectiveName("name");
//        adjustAdMapper.insert(entity);
    }
}
