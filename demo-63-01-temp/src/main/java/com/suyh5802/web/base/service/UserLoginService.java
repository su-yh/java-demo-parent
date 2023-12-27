package com.suyh5802.web.base.service;

import com.suyh5802.web.base.entity.UserLoginEntity;
import com.suyh5802.web.base.mapper.UserLoginMapper;
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
public class UserLoginService {
    private final UserLoginMapper userLoginMapper;

    @PostConstruct
    public void init() {
        List<UserLoginEntity> entities = userLoginMapper.selectList(null);
        System.out.println("entities: " + entities);
    }
}
