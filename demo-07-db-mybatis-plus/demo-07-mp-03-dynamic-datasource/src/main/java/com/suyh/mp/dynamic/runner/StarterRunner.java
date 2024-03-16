package com.suyh.mp.dynamic.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.suyh.mp.dynamic.entity.PersonalUniversalEntity;
import com.suyh.mp.dynamic.entity.UserInfoEntity;
import com.suyh.mp.dynamic.mapper.PersonalUniversalMapper;
import com.suyh.mp.dynamic.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author suyh
 * @since 2024-03-16
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class StarterRunner implements ApplicationRunner {
    private final UserInfoMapper userInfoMapper;
    private final PersonalUniversalMapper personalUniversalMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        {
            LambdaQueryWrapper<UserInfoEntity> queryWrapperUser = new LambdaQueryWrapper<>();
            List<UserInfoEntity> entities = userInfoMapper.selectList(queryWrapperUser);
            log.info("user info entities size: {}", entities.size());
        }

        {
            List<UserInfoEntity> entities = userInfoMapper.selectList();
            log.info("user info entities size: {}", entities.size());
        }

        {
            LambdaQueryWrapper<PersonalUniversalEntity> queryWrapperUser = new LambdaQueryWrapper<>();
            List<PersonalUniversalEntity> entities = personalUniversalMapper.selectList(queryWrapperUser);
            log.info("personal universal entities size: {}", entities.size());
        }


    }
}
