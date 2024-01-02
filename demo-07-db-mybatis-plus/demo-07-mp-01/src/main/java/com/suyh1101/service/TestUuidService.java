package com.suyh1101.service;

import com.suyh1101.entity.TestUuidEntity;
import com.suyh1101.mapper.TestUuidMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author suyh
 * @since 2023-12-09
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TestUuidService {
    private final TestUuidMapper testUuidMapper;

    @PostConstruct
    public void init() {
        TestUuidEntity entity = new TestUuidEntity();

        entity.setNickName("suyh").setAge(38);

        testUuidMapper.insert(entity);

        List<TestUuidEntity> entityList = testUuidMapper.selectList(null);

        System.out.println("entityList size: " + entityList.size());
    }
}
