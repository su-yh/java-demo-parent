package com.suyh5802.web.base.service;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.suyh5802.web.base.entity.WithdrawalEntity;
import com.suyh5802.web.base.enums.PN;
import com.suyh5802.web.base.mapper.WithdrawalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Random;

/**
 * @author suyh
 * @since 2023-12-27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WithdrawalService {
    private final WithdrawalMapper withdrawalMapper;

    @PostConstruct
    public void init() {
        // 是否启用下面的代码功能
        boolean enabled = false;
        if (!enabled) {
            return;
        }

        Random random = new Random();

        // 这些是根据那边提供的测试数据，来生成的有用的测试数据。
        String channelId01 = "slm_3000010";

        {
            long currentTimeMillis = System.currentTimeMillis();
            String channelId02 = "slg_1300230";
            String gaid02 = "ce52ee0f-6f2f-44c5-ae17-c0e8848aa768";

            for (PN pn : PN.values()) {
                Long uid = DefaultIdentifierGenerator.getInstance().nextId(null);
                Long vungoRechargeId = DefaultIdentifierGenerator.getInstance().nextId(null);

                WithdrawalEntity entity = new WithdrawalEntity();
                entity.setUid(uid + "").setCtime(currentTimeMillis).setAmount(new BigDecimal(random.nextInt(500)))
                        .setChannel(channelId02).setVungoWithdrawalId(vungoRechargeId)
                        .setOriginChannel(channelId01).setGaid(gaid02)
                        .setDay(1L).setOrder(uid + "").setCts(currentTimeMillis).setPn(pn.name()).setMtime(null)
                        .setLoginChannel(null).setRegisterChannel(null);

                withdrawalMapper.insert(entity);
            }
        }

        {
            long currentTimeMillis = System.currentTimeMillis();
            String channelId03 = "slg_1000054";
            String gaid03 = "f0dd4fae-77aa-4922-8988-099847ca2d68";

            for (PN pn : PN.values()) {
                Long uid = DefaultIdentifierGenerator.getInstance().nextId(null);
                Long vungoRechargeId = DefaultIdentifierGenerator.getInstance().nextId(null);

                WithdrawalEntity entity = new WithdrawalEntity();
                entity.setUid(uid + "").setCtime(currentTimeMillis).setAmount(new BigDecimal(random.nextInt(500)))
                        .setChannel(channelId03).setVungoWithdrawalId(vungoRechargeId)
                        .setOriginChannel(channelId01).setGaid(gaid03)
                        .setDay(1L).setOrder(uid + "").setCts(currentTimeMillis).setPn(pn.name()).setMtime(null)
                        .setLoginChannel(null).setRegisterChannel(null);

                withdrawalMapper.insert(entity);
            }
        }
    }
}
