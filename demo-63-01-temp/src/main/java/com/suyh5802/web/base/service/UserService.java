package com.suyh5802.web.base.service;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.suyh5802.web.base.entity.UserEntity;
import com.suyh5802.web.base.enums.PN;
import com.suyh5802.web.base.mapper.UserMapper;
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
public class UserService {
    private final UserMapper userMapper;

    // @PostConstruct
    public void initData() {
        String channelId01 = "slm_3000010";

        long currentTimeMillis = System.currentTimeMillis();

        {
            String channelId02 = "slg_1300230";
            String gaid02 = "ce52ee0f-6f2f-44c5-ae17-c0e8848aa768";

            for (PN pn : PN.values()) {
                Long uidNumber = DefaultIdentifierGenerator.getInstance().nextId(null);

                UserEntity entity = new UserEntity();
                entity.setUid(uidNumber + "").setChannel(channelId02).setCtime(currentTimeMillis).setGaid(gaid02)
                        .setOriginChannel(channelId01).setVungoUserId(1L).setPn(pn.name());

                userMapper.insert(entity);
            }
        }

        {
            String channelId03 = "slg_1000054";
            String gaid03 = "f0dd4fae-77aa-4922-8988-099847ca2d68";

            for (PN pn : PN.values()) {
                Long uidNumber = DefaultIdentifierGenerator.getInstance().nextId(null);

                UserEntity entity = new UserEntity();
                entity.setUid(uidNumber + "").setChannel(channelId03).setCtime(currentTimeMillis).setGaid(gaid03)
                        .setOriginChannel(channelId01).setVungoUserId(1L).setPn(pn.name());

                userMapper.insert(entity);
            }
        }
    }
}
