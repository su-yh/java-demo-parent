package com.suyh5802.web.base.service;

import com.suyh5802.web.base.entity.AdjustUserEntity;
import com.suyh5802.web.base.mapper.AdjustUserMapper;
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
public class AdjustUserService {
    private final AdjustUserMapper adjustUserMapper;

    @PostConstruct
    public void init() {
        List<AdjustUserEntity> entities = adjustUserMapper.selectList(null);
        System.out.println("entities: " + entities);
    }
}
