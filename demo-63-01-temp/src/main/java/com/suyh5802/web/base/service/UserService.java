package com.suyh5802.web.base.service;

import com.suyh5802.web.base.entity.UserEntity;
import com.suyh5802.web.base.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

/**
 * @author suyh
 * @since 2023-12-27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;

    @PostConstruct
    public void init() {
        List<UserEntity> entities = userMapper.selectList(null);
        System.out.println("entities: " + entities);

        String uuid = UUID.randomUUID().toString().replace("-", "");

        UserEntity entity = new UserEntity();
        entity.setUid(uuid).setChannel(uuid).setCtime(1L).setGaid(uuid)
                .setOriginChannel(uuid).setVungoUserId(1L)
                .setDay(1L).setCts(1L).setPn("py");

        userMapper.insert(entity);
    }
}
