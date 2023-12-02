package com.suyh0605.service;

import com.suyh0605.entity.EnumListEntity;
import com.suyh0605.enums.StatusEnum;
import com.suyh0605.mapper.EnumListMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author suyh
 * @since 2023-12-02
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TempService {
    private final EnumListMapper enumListMapper;

    @PostConstruct
    public void init() {
        EnumListEntity entity = new EnumListEntity();
        List<StatusEnum> statusList = Arrays.asList(StatusEnum.CLOSE, StatusEnum.OPEN, StatusEnum.CLOSE);
        entity.setStatusList(statusList);
        int resTemp = enumListMapper.insert(entity);
        log.info("insert result: {}", resTemp);
        List<EnumListEntity> listResult = enumListMapper.selectList();
        log.info("listResult: " + listResult);
    }
}
