package com.suyh5802.web.base.service;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.suyh5802.web.base.entity.UserLoginEntity;
import com.suyh5802.web.base.enums.PN;
import com.suyh5802.web.base.mapper.UserLoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author suyh
 * @since 2023-12-27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginService {
    private final UserLoginMapper userLoginMapper;

    @PostConstruct
    public void init() {
        // 是否启用下面的代码功能
        boolean enabled = false;
        if (!enabled) {
            return;
        }

        // 这些是根据那边提供的测试数据，来生成的有用的测试数据。
        String channelId01 = "slm_3000010";
        long day = 20220526L;

        long currentTimeMillis = System.currentTimeMillis();
        int src = 2;    // 1：用户注册；2：用户登录

        long curTimeSeconds = currentTimeMillis / 1000;

        for (int i = 0; i < 10000; i++) {
            {
                String channelId02 = "slg_1300230";

                for (PN pn : PN.values()) {
                    Long uidNumber = DefaultIdentifierGenerator.getInstance().nextId(null);
                    String gaid02 = uidNumber +"";
                    Long vungoUserLoginId = DefaultIdentifierGenerator.getInstance().nextId(null);

                    UserLoginEntity entity = new UserLoginEntity();
                    entity.setUid(uidNumber + "").setSrc(src).setChannel(channelId02).setCtime(curTimeSeconds).setGaid(gaid02)
                            .setOriginChannel(channelId01).setVungoUserLoginId(vungoUserLoginId).setPn(pn.name());
                    entity.setDay(day);

                    userLoginMapper.insert(entity);
                }
            }

            {
                String channelId03 = "slg_1000054";

                for (PN pn : PN.values()) {
                    Long uidNumber = DefaultIdentifierGenerator.getInstance().nextId(null);
                    String gaid03 = uidNumber +"";
                    Long vungoUserLoginId = DefaultIdentifierGenerator.getInstance().nextId(null);

                    UserLoginEntity entity = new UserLoginEntity();
                    entity.setUid(uidNumber + "").setSrc(src).setChannel(channelId03).setCtime(curTimeSeconds).setGaid(gaid03)
                            .setOriginChannel(channelId01).setVungoUserLoginId(vungoUserLoginId).setPn(pn.name());
                    entity.setDay(day);

                    userLoginMapper.insert(entity);
                }
            }
        }
    }
}
