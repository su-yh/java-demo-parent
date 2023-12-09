package com.suyh1101.service;

import com.suyh1101.entity.TestTableEntity;
import com.suyh1101.mapper.TestTableMapper;
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
public class TestTableService {
    private final TestTableMapper testTableMapper;

    @PostConstruct
    public void init() {
        TestTableEntity entity = new TestTableEntity();

        entity.setNickName("suyh").setAge(38);

        testTableMapper.insert(entity);

        List<TestTableEntity> entityList = testTableMapper.selectList(null);

        System.out.println("entityList size: " + entityList.size());
    }
}
