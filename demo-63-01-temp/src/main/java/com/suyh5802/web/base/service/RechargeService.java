package com.suyh5802.web.base.service;

import com.suyh5802.web.base.entity.RechargeEntity;
import com.suyh5802.web.base.mapper.RechargeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author suyh
 * @since 2023-12-27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RechargeService {
    private final RechargeMapper rechargeMapper;

    @PostConstruct
    public void init() {
        List<RechargeEntity> entities = rechargeMapper.selectList(null);
        System.out.println("entities: " + entities);

        String uuid = UUID.randomUUID().toString().replace("-", "");

        RechargeEntity entity = new RechargeEntity();
        entity.setUid(uuid).setCtime(1L).setGoodsAmt(new BigDecimal("2.2"))
                .setChannel(uuid).setChips(uuid).setVungoRechargeId(1L)
                .setGaid(uuid).setOriginChannel(uuid).setDay(1L)
                .setOrder(uuid).setCts(1L).setPn("py").setMtime(11L)
                .setLoginChannel(uuid).setRegisterChannel(uuid);

        rechargeMapper.insert(entity);
    }
}
