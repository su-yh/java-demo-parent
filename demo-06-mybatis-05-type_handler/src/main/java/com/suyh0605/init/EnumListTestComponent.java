package com.suyh0605.init;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.suyh0605.entity.EnumListEntity;
import com.suyh0605.enums.StatusEnum;
import com.suyh0605.mapper.EnumListMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author suyh
 * @since 2023-12-02
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class EnumListTestComponent implements ApplicationRunner {
    private final EnumListMapper enumListMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (true) {
            return;
        }

        {
            // 插入1 条测试数据
            EnumListEntity entity = new EnumListEntity();
            List<StatusEnum> statusList = Arrays.asList(StatusEnum.CLOSE, StatusEnum.OPEN, StatusEnum.CLOSE);
            entity.setStatusList(statusList).setFlag(true);
            int resTemp = enumListMapper.insert(entity);
            log.info("insert result: {}", resTemp);
            entity.setId(null).setFlag(null);
            resTemp = enumListMapper.insert(entity);
        }

        {
            if (true) {
                List<EnumListEntity> listResult = enumListMapper.selectList();
                if (listResult != null) {
                    for (EnumListEntity tempEntity : listResult) {
                        List<StatusEnum> enumList = tempEntity.getStatusList();
                        if (enumList != null) {
                            for (StatusEnum statusEnum : enumList) {
                                switch (statusEnum) {
                                    case OPEN:
                                        log.info("status enum value is open.");
                                        break;
                                    case CLOSE:
                                        log.info("status enum value is close.");
                                        break;
                                    default:
                                        log.info("status enum value is other value: {}", statusEnum);
                                        break;
                                }
                            }
                        }
                    }
                }
                log.info("listResult: " + listResult);
            }
        }

        {
            if (true) {
                // 验证使用boolean 类型的属性，在数据库中存成字符串时，查询条件没法正确匹配的问题
                LambdaQueryWrapper<EnumListEntity> queryWrapper =  new LambdaQueryWrapper<>();
                queryWrapper.eq(EnumListEntity::getFlag, Boolean.TRUE);
                List<EnumListEntity> resultList = enumListMapper.selectList(queryWrapper);
                if (resultList == null) {
                    resultList = new ArrayList<>();
                }

                log.info("conditional flag value is true, result list size: {}", resultList.size());
            }
            if (true) {
                // 验证使用boolean 类型的属性，在数据库中存成字符串时，查询条件没法正确匹配的问题
                LambdaQueryWrapper<EnumListEntity> queryWrapper =  new LambdaQueryWrapper<>();
                queryWrapper.eq(EnumListEntity::getFlag, Boolean.FALSE);
                List<EnumListEntity> resultList = enumListMapper.selectList(queryWrapper);
                if (resultList == null) {
                    resultList = new ArrayList<>();
                }

                log.info("conditional flag value is false, result list size: {}", resultList.size());
            }
        }
    }
}
