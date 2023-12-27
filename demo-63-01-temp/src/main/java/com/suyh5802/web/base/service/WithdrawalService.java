package com.suyh5802.web.base.service;

import com.suyh5802.web.base.mapper.WithdrawalMapper;
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
public class WithdrawalService {
    private final WithdrawalMapper withdrawalMapper;

    // @PostConstruct
    public void init() {
//        List<WithdrawalEntity> entities = withdrawalMapper.selectList(null);
//        System.out.println("entities: " + entities);
//
//        withdrawalMapper.delete(null);
//
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//
//        WithdrawalEntity entity = new WithdrawalEntity();
//        entity.setUid(uuid).setCtime(1L).setAmount(new BigDecimal("3.14")).setChannel(uuid)
//                .setVungoWithdrawalId(1L).setOriginChannel(uuid).setGaid(uuid)
//                .setDay(1L).setOrder(uuid).setCts(1L).setPn("py").setMtime(1L)
//                .setLoginChannel(uuid).setRegisterChannel(uuid);
//
//        withdrawalMapper.insert(entity);
    }
}
