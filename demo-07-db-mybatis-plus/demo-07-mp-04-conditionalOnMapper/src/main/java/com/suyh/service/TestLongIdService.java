package com.suyh.service;

import com.suyh.entity.TestLongIdEntity;
import com.suyh.mapper.TestLongIdMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author suyh
 * @since 2023-12-09
 */
@RequiredArgsConstructor
@Slf4j
public class TestLongIdService {
    private final TestLongIdMapper testLongIdMapper;

    @PostConstruct
    public void init() {
        TestLongIdEntity entity = new TestLongIdEntity();

        entity.setNickName("suyh").setAge(38);

        testLongIdMapper.insert(entity);

        List<TestLongIdEntity> entityList = testLongIdMapper.selectList(null);

        System.out.println("entityList size: " + entityList.size());
    }
}
