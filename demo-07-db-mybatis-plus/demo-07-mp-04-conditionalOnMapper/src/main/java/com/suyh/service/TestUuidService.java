package com.suyh.service;

import com.suyh.entity.TestUuidEntity;
import com.suyh.mapper.TestUuidMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author suyh
 * @since 2023-12-09
 */
@RequiredArgsConstructor
@Slf4j
public class TestUuidService {
    private final TestUuidMapper testUuidMapper;
    @Setter
    @Getter
    // 加这个只是为了在使用 BeanDefinitionBuilder 时，有多一个builder 方法调用示例保留。
    private Boolean enabled = true;

    @PostConstruct
    public void init() {
        if (!enabled) {
            return;
        }

        TestUuidEntity entity = new TestUuidEntity();

        entity.setNickName("suyh").setAge(38);

        testUuidMapper.insert(entity);

        List<TestUuidEntity> entityList = testUuidMapper.selectList(null);

        System.out.println("entityList size: " + entityList.size());
    }
}
