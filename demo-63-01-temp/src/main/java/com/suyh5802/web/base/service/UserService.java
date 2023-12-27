package com.suyh5802.web.base.service;

import com.suyh5802.web.base.entity.UserEntity;
import com.suyh5802.web.base.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

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
    }
}
