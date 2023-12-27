package com.suyh5802.web.base.service;

import com.suyh5802.web.base.entity.AdjustAdEntity;
import com.suyh5802.web.base.mapper.AdjustAdMapper;
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
public class AdjustAdService {
    private final AdjustAdMapper adjustAdMapper;

    @PostConstruct
    public void init() {
        List<AdjustAdEntity> entities = adjustAdMapper.selectList(null);
        System.out.println("entities: " + entities);
    }
}
