package com.suyh0605.init;

import com.suyh0605.entity.EnumListEntity;
import com.suyh0605.enums.StatusEnum;
import com.suyh0605.mapper.EnumListMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
        if (false) {
            return;
        }

        EnumListEntity entity = new EnumListEntity();
        List<StatusEnum> statusList = Arrays.asList(StatusEnum.CLOSE, StatusEnum.OPEN, StatusEnum.CLOSE);
        entity.setStatusList(statusList).setFlag(true);
        int resTemp = enumListMapper.insert(entity);
        log.info("insert result: {}", resTemp);
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
